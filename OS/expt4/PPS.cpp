#include <iostream>
#include <string>
#include <algorithm>
#include <iomanip>
#include <vector>
#include <climits>
using namespace std;

class Process {
public:
    string name;
    double arrival_time;
    double burst_time;
    double turn_around_time;
    double waiting_time;
    double completion_time;
    double remaining_time;
    int priority;
    bool done;

    Process() {
        name = "";
        arrival_time = 0.0;
        burst_time = 0.0;
        waiting_time = -1;
        turn_around_time = -1;
        completion_time = -1;
        remaining_time = 0.0;
        priority = 0;
        done = false;
    }

    Process(string _name, double _burst_time, double _arrival_time = 0, int _priority = -1) {
        name = _name;
        arrival_time = _arrival_time;
        burst_time = _burst_time;
        priority = (_priority == -1) ? static_cast<int>(burst_time) : _priority;
        waiting_time = -1;
        turn_around_time = -1;
        completion_time = -1;
        remaining_time = burst_time;
        done = false;
    }

    void display() const {
        cout << setw(7) << name;
        cout << setw(10) << arrival_time;
        cout << setw(10) << burst_time;
        cout << setw(10) << priority;
        cout << setw(12) << turn_around_time;
        cout << setw(10) << waiting_time;
        cout << endl;
    }
};

int selectProcess(Process *p, int n, double currtime) {
    int index = -1;
    int min_priority = INT_MAX;
    for (int i = 0; i < n; i++) {
        if (p[i].arrival_time <= currtime && p[i].remaining_time > 0 && p[i].priority < min_priority) {
            min_priority = p[i].priority;
            index = i;
        }
    }
    return index;
}

void Schedule(Process *p, int n, double &average_turn_around_time, double &average_waiting_time) {
    if (!p || n <= 0) return;

    sort(p, p + n, [](const Process &a, const Process &b) {
        return a.arrival_time < b.arrival_time;
    });

    double currtime = p[0].arrival_time;
    double total_turn_around_time = 0.0;
    double total_waiting_time = 0.0;
    double reqd_time = 0.0;

    for (int i = 0; i < n; i++) {
        reqd_time += p[i].burst_time;
    }

    cout << "\nGantt Chart:\n";
    cout << "  ";
    for (int i = 0; i < reqd_time; i++) {
        int index = selectProcess(p, n, currtime);
        if (index != -1) {
            currtime += 1.0;
            cout << setw(3) << p[index].name << "  ";
            p[index].remaining_time -= 1.0;
            if (p[index].remaining_time == 0) {
                p[index].completion_time = currtime;
                p[index].turn_around_time = currtime - p[index].arrival_time;
                p[index].waiting_time = p[index].turn_around_time - p[index].burst_time;
                total_turn_around_time += p[index].turn_around_time;
                total_waiting_time += p[index].waiting_time;
            }
        }
    }
    cout << "\n";
    for (int i = static_cast<int>(p[0].arrival_time); i <= static_cast<int>(reqd_time + p[0].arrival_time); i++) {
        cout << setw(2) << i << "   ";
    }
    cout << endl;

    average_turn_around_time = total_turn_around_time / n;
    average_waiting_time = total_waiting_time / n;
}

int main() {
    int n;
    cout << "Enter number of processes: ";
    cin >> n;

    Process *p = new Process[n];
    for (int i = 0; i < n; i++) {
        string name;
        double burst, arrival;
        int priority;
        cout << "\nProcess " << i + 1 << ":\n";
        cout << "Enter process name: ";
        cin >> name;
        cout << "Enter arrival time: ";
        cin >> arrival;
        cout << "Enter burst time: ";
        cin >> burst;
        cout << "Enter Priority (or -1 if not given): ";
        cin >> priority;
        p[i] = Process(name, burst, arrival, priority);
    }

    double avg_tat, avg_wt;
    Schedule(p, n, avg_tat, avg_wt);

    cout << "--------------------------------------------------------------\n";
    cout << setw(10) << "Process" << setw(10) << "Arrival" << setw(10) << "Burst" << setw(10) << "Priority" << setw(10) << "TAT" << setw(10) << "WT\n";
    cout << "--------------------------------------------------------------\n";
    for (int i = 0; i < n; i++) {
        p[i].display();
    }
    cout << "--------------------------------------------------------------\n";

    cout << "ATAT: " << avg_tat << endl;
    cout << "AWT : " << avg_wt << endl << endl;

    delete[] p;
    return 0;
}
