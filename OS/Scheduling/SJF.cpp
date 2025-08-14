# include<iostream>
#include<string>
#include<algorithm>
using namespace std;


class Process{
    string name;
    double arrival_time;
    double burst_time;
    double turn_around_time;
    double waiting_time;
    public:

    Process(){
        name="";
        arrival_time=0.0;
        burst_time=0.0;
        waiting_time=-1;
        turn_around_time=-1;
    }
    Process(string _name, double _burst_time,double _arrival_time=0){
        name=_name;
        arrival_time=_arrival_time;
        burst_time=_burst_time;
    }
    friend void Schedule(Process *p, int n, double&,double&);
};

void Schedule(Process *p, int n, double& average_turn_around_time, double& average_waiting_time){
    if(!p) return;
    sort(p, p + n, [](const Process& a, const Process& b) {
        if (a.arrival_time != b.arrival_time)
            return a.arrival_time < b.arrival_time;
        return a.burst_time < b.burst_time;
    });
    double completion_time=0;
    double total_turn_around_time=0;
    double total_waiting_time=0;

    for(int i=0;i<n;i++){
        completion_time+=p[i].burst_time;
        p[i].turn_around_time=completion_time-p[i].arrival_time;
        p[i].waiting_time=p[i].turn_around_time-p[i].burst_time;
        total_turn_around_time+=p[i].turn_around_time;
        total_waiting_time+=p[i].waiting_time;
    }
    average_turn_around_time=total_turn_around_time/n;
    average_waiting_time=total_waiting_time/n;
}


int main(){

int n;
cout << "Enter number of processes: ";
cin >> n;

Process *p = new Process[n];
for(int i = 0; i < n; i++) {
    string name;
    double burst, arrival;
    cout << "\nProcess " << i+1 << ":\n";
    cout << "Enter process name: ";
    cin >> name;
    cout << "Enter arrival time: ";
    cin >> arrival;
    cout << "Enter burst time: ";
    cin >> burst;
    p[i] = Process(name, burst, arrival);
}

double avg_tat, avg_wt;
Schedule(p, n, avg_tat, avg_wt);

cout << "\nResults:\n";
cout << "Average Turn Around Time: " << avg_tat << endl;
cout << "Average Waiting Time: " << avg_wt << endl;

delete[] p;
return 0;
}