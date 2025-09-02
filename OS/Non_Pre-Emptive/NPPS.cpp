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
    int priority;
    bool done;
    public:

    Process(){
        name="";
        arrival_time=0.0;
        burst_time=0.0;
        waiting_time=-1;
        turn_around_time=-1;
        completion_time=-1;
        priority=100;
        done=false;
    }
    Process(string _name, double _burst_time, int _priority, double _arrival_time=0){
        name=_name;
        arrival_time=_arrival_time;
        burst_time=_burst_time;
        priority=_priority;
        waiting_time=-1;
        turn_around_time=-1;
        completion_time=-1;
        done=false;
    }

    void display(){
        cout<<setw(7)<<name;
        cout<<setw(10)<<arrival_time;
        cout<<setw(10)<<burst_time;
        cout<<setw(12)<<turn_around_time;
        cout<<setw(10)<<waiting_time;
        cout<<endl;
    }
    friend Process selectProcess(Process *p,int n, double current_time);
    friend void Schedule(Process *p, int n, double&,double&);
};

Process selectProcess(Process *p, int n, double current_time){
    Process selected;
    double min_priority=100;
    for(int i = 0; i < n; i++) {
        if(p[i].waiting_time == -1 && p[i].arrival_time <= current_time && p[i].priority < min_priority && !p[i].done) {
            min_priority = p[i].priority;
            selected = p[i];
        }
    }
    return selected;
}

void Schedule(Process *p, int n, double& average_turn_around_time, double& average_waiting_time){
   if(!p) return;

   sort(p,p+n, [](Process a, Process b){
       if(a.arrival_time==b.arrival_time) return a.priority < b.priority;
       return a.arrival_time < b.arrival_time;
   });
    double current_time = p[0].arrival_time;
   double total_turn_around_time = 0;
   double total_waiting_time = 0;
   double completion_time = p[0].arrival_time;

   vector<Process> v;

   for(int i=0;i<n;i++){
       Process curr=selectProcess(p, n, current_time);
       if(curr.name!=""){
            completion_time+=curr.burst_time;
           current_time+=curr.burst_time;
           curr.completion_time=completion_time;
           curr.turn_around_time=curr.completion_time-curr.arrival_time;
           curr.waiting_time=curr.turn_around_time-curr.burst_time;
           total_turn_around_time += curr.turn_around_time;
           total_waiting_time += curr.waiting_time;
           curr.done=true;
           // Find and mark the original process as done
           for(int j=0; j<n; j++){
               if(p[j].name == curr.name){
                   p[j].done = true;
                   p[j].completion_time = curr.completion_time;
                   p[j].turn_around_time = curr.turn_around_time;
                   p[j].waiting_time = curr.waiting_time;
                   break;
               }
           }
           v.push_back(curr);
       }
   }

    cout<<"\n";
    cout<<"Gantt Chart:\n";
    for (auto i:v) {
        cout << setw(6) << i.name << " ";
    }
    cout << endl;
    cout<<"  ";
    cout << v[0].arrival_time;
    
    for (int i = 0; i < v.size(); i++)
    {
        cout << setw(6) << v[i].completion_time << " ";
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
    int priority;
    cout << "\nProcess " << i+1 << ":\n";
    cout << "Enter process name: ";
    cin >> name;
    cout << "Enter arrival time: ";
    cin >> arrival;
    cout << "Enter burst time: ";
    cin >> burst;
    cout << "Enter Priority: ";
    cin >> priority;
    p[i] = Process(name, burst, priority, arrival);
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