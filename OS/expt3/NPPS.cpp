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
    int priority;      
    bool done;

    Process() {
        name = "";
        arrival_time = 0.0;
        burst_time = 0.0;
        waiting_time = -1;
        turn_around_time = -1;
        completion_time = -1;
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
        done = false;
    }

    void display() const {
        cout << setw(7) << name;
        cout << setw(10) << arrival_time;
        cout << setw(10) << burst_time;
        cout << setw(12) << turn_around_time;
        cout << setw(10) << waiting_time;
        cout << endl;
    }
};

int selectProcess(Process *p, int n, double current_time) {
    int idx = -1;
    int best_priority = INT_MAX;
    for (int i = 0; i < n; i++) {
        if (!p[i].done && p[i].arrival_time <= current_time) {
            if (p[i].priority < best_priority) {
                best_priority = p[i].priority;
                idx = i;
            }
        }
    }
    return idx;
}

void Schedule(Process *p, int n, double &average_turn_around_time, double &average_waiting_time) {
    if (!p || n <= 0) return;

    sort(p, p + n, [](const Process &a, const Process &b) {
        if (a.arrival_time == b.arrival_time) return a.priority < b.priority;
        return a.arrival_time < b.arrival_time;
    });

    double current_time = p[0].arrival_time;
    double total_turn_around_time = 0.0;
    double total_waiting_time = 0.0;
    int finished = 0;

    vector<pair<string, double>> timeline;

    while (finished < n) {
        int idx = selectProcess(p, n, current_time);
        if (idx == -1) {
            double next_arrival = 1e18;
            for (int i = 0; i < n; i++) {
                if (!p[i].done) next_arrival = min(next_arrival, p[i].arrival_time);
            }
            if (next_arrival == 1e18) break;
            current_time = max(current_time, next_arrival);
            continue;
        }
        current_time += p[idx].burst_time;
        p[idx].completion_time = current_time;
        p[idx].turn_around_time = p[idx].completion_time - p[idx].arrival_time;
        p[idx].waiting_time = p[idx].turn_around_time - p[idx].burst_time;
        p[idx].done = true;

        total_turn_around_time += p[idx].turn_around_time;
        total_waiting_time += p[idx].waiting_time;
        finished++;

        timeline.push_back({p[idx].name, p[idx].completion_time});
    }

    cout << "\nGantt Chart:\n";
    for (auto &e : timeline) cout << setw(6) << e.first << " ";
    cout << "\n  ";

    double chartStart = p[0].arrival_time;
    for (int i = 1; i < n; ++i) chartStart = min(chartStart, p[i].arrival_time);
    cout << chartStart;
    for (auto &e : timeline) cout << setw(6) << e.second << " ";
    cout << "\n";

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

    cout << "----------------------------------------------------\n";
    cout << setw(10) << "Process" << setw(10) << "Arrival" << setw(10) << "Burst" << setw(10) << "TAT" << setw(10) << "WT\n";
    cout << "----------------------------------------------------\n";
    for (int i = 0; i < n; i++) {
        p[i].display();
    }
    cout << "----------------------------------------------------\n";

    cout << "ATAT: " << avg_tat << endl;
    cout << "AWT : " << avg_wt << endl << endl;

    delete[] p;
    return 0;
}