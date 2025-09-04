#include <iostream>
#include <string>
#include <algorithm>
#include <iomanip>
#include <vector>
#include <limits>

using namespace std;

class Process {
    string name;
    double arrival_time;
    double burst_time;
    double turn_around_time;
    double waiting_time;
    double completion_time;
    int priority;   // higher number => higher priority
    bool done;

public:
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
        if (_priority == -1) {
            // Higher burst time => lower priority number (derived).
            // Keep within int range but allow negative too (selection handles it).
            priority = 100 - static_cast<int>(burst_time);
        } else {
            priority = _priority;
        }
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

    // Friend helpers to access private members
    friend int selectProcessIndex(Process* p, int n, double current_time);
    friend void Schedule(Process* p, int n, double&, double&);
};

// Select index of the highest-priority arrived process; return -1 if none ready.
int selectProcessIndex(Process* p, int n, double current_time) {
    int sel = -1;
    int bestPriority = numeric_limits<int>::min();

    for (int i = 0; i < n; i++) {
        if (!p[i].done && p[i].arrival_time <= current_time) {
            if (p[i].priority > bestPriority) {
                sel = i;
                bestPriority = p[i].priority;
            } else if (p[i].priority == bestPriority && sel != -1) {
                // Tie-breakers (optional but deterministic):
                // 1) Earlier arrival first
                if (p[i].arrival_time < p[sel].arrival_time) {
                    sel = i;
                }
                // 2) If same arrival, shorter burst first
                else if (p[i].arrival_time == p[sel].arrival_time &&
                         p[i].burst_time < p[sel].burst_time) {
                    sel = i;
                }
            } else if (p[i].priority == bestPriority && sel == -1) {
                sel = i;
            }
        }
    }
    return sel;
}

void Schedule(Process* p, int n, double& average_turn_around_time, double& average_waiting_time) {
    if (!p || n <= 0) return;

    // Find earliest arrival to start the clock
    double current_time = numeric_limits<double>::infinity();
    for (int i = 0; i < n; i++) {
        current_time = min(current_time, p[i].arrival_time);
    }

    int completed = 0;
    double total_turn_around_time = 0.0;
    double total_waiting_time = 0.0;

    struct Slot {
        string name;
        double start;
        double end;
    };
    vector<Slot> gantt;

    while (completed < n) {
        int idx = selectProcessIndex(p, n, current_time);
        if (idx == -1) {
            // No process is ready; jump to the next arrival and mark idle time
            double next_arrival = numeric_limits<double>::infinity();
            for (int i = 0; i < n; i++) {
                if (!p[i].done) {
                    next_arrival = min(next_arrival, p[i].arrival_time);
                }
            }
            if (next_arrival == numeric_limits<double>::infinity()) {
                break; // no remaining processes
            }
            // Add idle gap for the gantt chart
            if (gantt.empty() || gantt.back().end < next_arrival) {
                double idleStart = gantt.empty() ? current_time : gantt.back().end;
                if (idleStart < next_arrival) {
                    gantt.push_back({"IDLE", idleStart, next_arrival});
                }
            }
            current_time = next_arrival;
            continue;
        }

        // Schedule the selected process
        double start_time = current_time; // non-preemptive; ready at/<= current_time
        double end_time = start_time + p[idx].burst_time;

        p[idx].completion_time = end_time;
        p[idx].turn_around_time = p[idx].completion_time - p[idx].arrival_time;
        p[idx].waiting_time = p[idx].turn_around_time - p[idx].burst_time;
        p[idx].done = true;

        total_turn_around_time += p[idx].turn_around_time;
        total_waiting_time += p[idx].waiting_time;

        gantt.push_back({p[idx].name, start_time, end_time});

        current_time = end_time;
        completed++;
    }

    // Gantt Chart
    cout << "\nGantt Chart:\n";
    for (const auto& s : gantt) {
        cout << setw(6) << s.name << " ";
    }
    cout << "\n";
    if (!gantt.empty()) {
        cout << "  " << gantt.front().start;
        for (const auto& s : gantt) {
            cout << setw(6) << s.end << " ";
        }
        cout << "\n";
    }

    average_turn_around_time = total_turn_around_time / n;
    average_waiting_time = total_waiting_time / n;
}

int main() {
    int n;
    cout << "Enter number of processes: ";
    cin >> n;

    Process* p = new Process[n];
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

    double avg_tat = 0.0, avg_wt = 0.0;
    Schedule(p, n, avg_tat, avg_wt);

    cout << "----------------------------------------------------\n";
    cout << setw(10) << "Process" << setw(10) << "Arrival" << setw(10) << "Burst"
         << setw(10) << "TAT" << setw(10) << "WT\n";
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