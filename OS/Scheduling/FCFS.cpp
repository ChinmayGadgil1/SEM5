# include<iostream>
#include<string>
#include<algorithm>
#include<iomanip>
#include<vector>
using namespace std;

class Process{
    string name;
    double arrival_time;
    double burst_time;
    double turn_around_time;
    double waiting_time;
    double completion_time;
    public:

    Process(){
        name="";
        arrival_time=0.0;
        burst_time=0.0;
        waiting_time=-1;
        turn_around_time=-1;
        completion_time=-1;
    }
    Process(string _name, double _burst_time,double _arrival_time=0){
        name=_name;
        arrival_time=_arrival_time;
        burst_time=_burst_time;

    }

    void display(){
        cout<<setw(7)<<name;
        cout<<setw(10)<<arrival_time;
        cout<<setw(10)<<burst_time;
        cout<<setw(12)<<turn_around_time;
        cout<<setw(10)<<waiting_time;
        cout<<endl;
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
    double completion_time=p[0].arrival_time;
    double total_turn_around_time=0;
    double total_waiting_time=0;

    for(int i=0;i<n;i++){
        completion_time+=p[i].burst_time;
        p[i].completion_time = completion_time;
        p[i].turn_around_time=completion_time-p[i].arrival_time;
        p[i].waiting_time=p[i].turn_around_time-p[i].burst_time;
        total_turn_around_time+=p[i].turn_around_time;
        total_waiting_time+=p[i].waiting_time;
    }
    cout<<"\n";
    cout<<"Gantt Chart:\n";
    for (int i = 0; i < n; i++) {
        cout << setw(6) << p[i].name << " ";
    }
    cout << endl;
    cout<<"  ";
    cout << p[0].arrival_time;
    
    for (int i = 0; i < n; i++)
    {
        cout << setw(6) << p[i].completion_time << " ";
    }
    cout << endl;
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


cout<<"----------------------------------------------------\n";
cout<<setw(10)<<"Process"<<setw(10)<<"Arrival"<<setw(10)<<"Burst"<<setw(10)<<"TAT"<<setw(10)<<"WT\n";
cout<<"----------------------------------------------------\n";
for (int i = 0; i < n; i++)
{
    p[i].display();
}
cout<<"----------------------------------------------------\n";

cout << "ATAT: " << avg_tat << endl;
cout << "AWT : " << avg_wt << endl<<endl;

delete[] p;
return 0;
}