# include<iostream>
#include<string>
#include<algorithm>
#include<iomanip>
#include<queue>
using namespace std;


class Process{
    string name;
    double arrival_time;
    double burst_time;
    double turn_around_time;
    double waiting_time;
    double remaining_time;
    double completion_time;
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

     void display(){
        cout<<setw(7)<<name;
        cout<<setw(10)<<arrival_time;
        cout<<setw(10)<<burst_time;
        cout<<setw(12)<<turn_around_time;
        cout<<setw(10)<<waiting_time;
        cout<<endl;
    }

    friend void Schedule(Process *p, int n, double&,double&,int);
    friend int selectProcess(Process*,int,double);
};

int addToReady(Process *p, int n, double currtime,queue<int>& q){
    for (int i = 0; i < n; i++)
    {
        if(p[i].arr)
    }
    
}



void Schedule(Process *p, int n, double& average_turn_around_time, double& average_waiting_time,int time_quantum){
    queue<int> readyQueue;
    double time_needed=0;
    for(int i=0;i<n;i++){
        time_needed+=p[i].burst_time;
    }

    sort(p,p+n,[](Process &a,Process &b){
        return a.arrival_time<b.arrival_time;
    });

    int currtime= p[0].arrival_time;
    int completion_time,total_turn_around_time=0,total_waiting_time=0;
    for (int i = currtime; i <= time_needed; i+=time_quantum)
    {
        currtime+=time_quantum;
        addToReady(p,n,currtime,readyQueue);
        int index=readyQueue.front();
        if(p[index].remaining_time<time_quantum){
            currtime=currtime-time_quantum+p[index].remaining_time;
            p[index].remaining_time=0;
        }else{
            p[index].remaining_time-=time_quantum;
        }
        if(p[index].remaining_time==0){
                completion_time=currtime;
                p[index].completion_time=completion_time;
                p[index].turn_around_time=completion_time-p[index].arrival_time;
                p[index].waiting_time=p[index].turn_around_time-p[index].burst_time;
                total_turn_around_time+=p[index].turn_around_time;
                total_waiting_time+=p[index].waiting_time;
        }

    }
    
    
}


int main(){

int n,time_quantum;
cout << "Enter number of processes: ";
cin >> n;
cout<<"Enter time quantum: ";
cin>>time_quantum;
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
Schedule(p, n, avg_tat, avg_wt,time_quantum);

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