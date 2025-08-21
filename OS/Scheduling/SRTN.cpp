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
    double remaining_time;
    public:

    Process(){
        name="";
        arrival_time=0.0;
        burst_time=0.0;
        waiting_time=-1;
        turn_around_time=-1;
        remaining_time=0;
    }
    Process(string _name, double _burst_time,double _arrival_time=0){
        name=_name;
        arrival_time=_arrival_time;
        burst_time=_burst_time;
        remaining_time=burst_time;
    }
    friend void Schedule(Process *p, int n, double&,double&);
    friend int selectProcess(Process*,int,double);
};

int selectProcess(Process *p, int n, double currtime){
    int index=-1;
    double min_burst=1000000;
    for(int i=0;i<n;i++){
        if(p[i].arrival_time<=currtime && p[i].remaining_time>0 && p[i].remaining_time<min_burst){
            min_burst=p[i].remaining_time;
            index=i;
        }
    }
    return index;
}

void Schedule(Process *p, int n, double& average_turn_around_time, double& average_waiting_time){

    if(!p) return;
    sort(p, p + n, [](const Process& a, const Process& b) {
        return a.arrival_time < b.arrival_time;
    });

    double currtime=0;
    double completion_time=0;
    double total_turn_around_time=0;
    double total_waiting_time=0;
    double reqd_time=0;

    for (int i = 0; i < n; i++)
    {
        reqd_time+=p[i].burst_time;
    }
    

    for(int i=0;i<reqd_time;i++){
        int index=selectProcess(p,n,currtime);
        if(index!=-1){
            currtime++;
            p[index].remaining_time--;
            if(p[index].remaining_time==0){
                completion_time=currtime;
                p[index].turn_around_time=completion_time-p[index].arrival_time;
                p[index].waiting_time=p[index].turn_around_time-p[index].burst_time;
                total_turn_around_time+=p[index].turn_around_time;
                total_waiting_time+=p[index].waiting_time;
            }
        }
    }
    cout<<total_turn_around_time<<endl;
    cout<<total_waiting_time<<endl;
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