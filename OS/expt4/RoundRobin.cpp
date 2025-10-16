#include <iostream>
#include <string>
#include <algorithm>
#include <iomanip>
#include <queue>
#include <vector>
using namespace std;

class Process
{
public:
    string name;
    double arrival_time;
    double burst_time;
    double turn_around_time;
    double waiting_time;
    double completion_time;
    double remaining_time;

    Process()
    {
        name = "";
        arrival_time = 0.0;
        burst_time = 0.0;
        waiting_time = -1;
        turn_around_time = -1;
        completion_time = -1;
        remaining_time = 0.0;
    }

    Process(string _name, double _burst_time, double _arrival_time = 0)
    {
        name = _name;
        arrival_time = _arrival_time;
        burst_time = _burst_time;
        waiting_time = -1;
        turn_around_time = -1;
        completion_time = -1;
        remaining_time = burst_time;
    }

    void display() const
    {
        cout << setw(7) << name;
        cout << setw(10) << arrival_time;
        cout << setw(10) << burst_time;
        cout << setw(12) << turn_around_time;
        cout << setw(10) << waiting_time;
        cout << endl;
    }
};

void addToReady(Process *p, int n, double currtime, queue<int> &q, vector<bool> &arrived)
{
    for (int i = 0; i < n; i++)
    {
        if (!arrived[i] && p[i].arrival_time <= currtime && p[i].remaining_time > 0)
        {
            q.push(i);
            arrived[i] = true;
        }
    }
}

void Schedule(Process *p, int n, double &average_turn_around_time,
              double &average_waiting_time, int time_quantum,
              vector<pair<string, pair<double, double>>> &gantt)
{

    queue<int> readyQueue;
    vector<bool> arrived(n, false);
    double currtime = 0;
    int completed = 0;
    double total_turn_around_time = 0.0, total_waiting_time = 0.0;

    sort(p, p + n, [](const Process &a, const Process &b)
         { return a.arrival_time < b.arrival_time; });

    currtime = p[0].arrival_time;
    addToReady(p, n, currtime, readyQueue, arrived);

    while (completed < n)
    {
        if (readyQueue.empty())
        {
            double next_arrival = 1e9;
            for (int i = 0; i < n; i++)
                if (p[i].remaining_time > 0 && p[i].arrival_time > currtime)
                    next_arrival = min(next_arrival, p[i].arrival_time);
            currtime = next_arrival;
            addToReady(p, n, currtime, readyQueue, arrived);
            continue;
        }

        int idx = readyQueue.front();
        readyQueue.pop();

        double start = currtime;
        double exec_time = min((double)time_quantum, p[idx].remaining_time);
        currtime += exec_time;
        p[idx].remaining_time -= exec_time;
        gantt.push_back({p[idx].name, {start, currtime}});

        addToReady(p, n, currtime, readyQueue, arrived);

        if (p[idx].remaining_time > 0)
            readyQueue.push(idx);
        else
        {
            p[idx].completion_time = currtime;
            p[idx].turn_around_time = p[idx].completion_time - p[idx].arrival_time;
            p[idx].waiting_time = p[idx].turn_around_time - p[idx].burst_time;
            total_turn_around_time += p[idx].turn_around_time;
            total_waiting_time += p[idx].waiting_time;
            completed++;
        }
        cout << "RQ: ";
        if (readyQueue.empty())
            cout << "Empty";
        else
        {
            queue<int> temp = readyQueue;
            while (!temp.empty())
            {
                cout << p[temp.front()].name << " ";
                temp.pop();
            }
        }
        cout << endl;
    }

    average_turn_around_time = total_turn_around_time / n;
    average_waiting_time = total_waiting_time / n;
}

int main()
{
    int n, time_quantum;
    cout << "Enter number of processes: ";
    cin >> n;
    cout << "Enter time quantum: ";
    cin >> time_quantum;

    Process *p = new Process[n];
    for (int i = 0; i < n; i++)
    {
        string name;
        double burst, arrival;
        cout << "\nProcess " << i + 1 << ":\n";
        cout << "Enter process name: ";
        cin >> name;
        cout << "Enter arrival time: ";
        cin >> arrival;
        cout << "Enter burst time: ";
        cin >> burst;
        p[i] = Process(name, burst, arrival);
    }

    double avg_tat, avg_wt;
    vector<pair<string, pair<double, double>>> gantt;
    Schedule(p, n, avg_tat, avg_wt, time_quantum, gantt);

    cout << "\n\nGantt Chart:\n ";
    for (auto &g : gantt)
        cout << "| " << setw(4) << g.first << " ";
    cout << "|\n";

    cout << fixed << setprecision(2);
    cout << gantt[0].second.first;
    for (auto &g : gantt)
        cout << setw(7) << g.second.second;
    cout << endl;

    cout << "----------------------------------------------------\n";
    cout << setw(10) << "Process" << setw(10) << "Arrival"
         << setw(10) << "Burst" << setw(12) << "TAT" << setw(10) << "WT\n";
    cout << "----------------------------------------------------\n";
    for (int i = 0; i < n; i++)
        p[i].display();
    cout << "----------------------------------------------------\n";

    cout << "ATAT: " << avg_tat << endl;
    cout << "AWT : " << avg_wt << endl;

    delete[] p;
    return 0;
}