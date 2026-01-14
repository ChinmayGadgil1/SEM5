#include<iostream>
#include<vector>
#include<algorithm>
#include<iomanip>
using namespace std;

class Process;
class Gantt{
    public:
    string name;
    int i,j;
    Gantt(string n,int i,int j){
        name=n;
        this->i=i;
        this->j=j;
    }
    friend void schedule(vector<Process>&,double& avg_tat,double& avg_wt,vector<Gantt>&g);
    friend void displayGantt(vector<Gantt>&g);
};
class Process{
    public:
    string name;
    double at;
    double bt;
    double tat;
    double wt;

    Process(){
        tat=0;
        bt=0;
    }
    Process(string s,double a,double b,double tat=0,double wt=0){
        name=s;
        at=a;
        bt=b;
        tat=tat;
        wt=wt;
    }
    friend void schedule(vector<Process>&,double& avg_tat,double& avg_wt,vector<Gantt>&g);
    friend void displayTable(vector<Process>&,double& avg_tat,double& avg_wt);
};



void displayTable(vector<Process>&p,double& avg_tat,double& avg_wt){
    cout<<"PROCESS CHART\n";
    cout<<" NAME "<<"  AT  "<<"  BT  "<<"  TT  "<<"  WT  \n";
    for(auto pr:p){
        cout<<setw(6)<<pr.name;
        cout<<setw(6)<<pr.at;
        cout<<setw(6)<<pr.bt;
        cout<<setw(6)<<pr.tat;
        cout<<setw(6)<<pr.wt;
        cout<<"\n";
    }
    cout<<"\n";
    cout<<"ATAT: "<<avg_tat<<"\nAWT: "<<avg_wt<<endl;
}

void schedule(vector<Process>& p,double& avg_tat,double& avg_wt,vector<Gantt>&g){
    sort(p.begin(),p.end(),[](Process& a, Process& b){
        if(a.at!=b.at)
            return a.at < b.at;
        return a.bt<b.bt;
    });

    double ct=p[0].at;
    int n=p.size();
    for(int i=0;i<n;i++){
        ct+=p[i].bt;
        p[i].tat=ct-p[i].at;
        p[i].wt=p[i].tat-p[i].bt;
        g.push_back(Gantt(p[i].name,ct-p[i].bt,ct));
    }

    double ttat=0;
    double twt=0;

    for(auto pr:p){
        ttat+=pr.tat;
        twt+=pr.wt;
    }

    avg_tat=ttat/n;
    avg_wt=twt/n;
}

void displayGantt(vector<Gantt>&gc){

    int n=gc.size();
    cout<<"  ";
    for(auto g:gc){
        cout<<g.name<<"   ";
    }
    cout<<endl;
    cout<<gc[0].i<<"   ";
    for(int i=1;i<n;i++){
        cout<<gc[i].i<<"   ";
    }
    cout<<gc[n-1].j;
    cout<<endl;
}

int main(){
    int n;
    double avg_tat,avg_wt;
    cout<<"Enter number of processes:";
    cin>>n;
    vector<Process> p;
    vector<Gantt> g;

    cout<<"Enter pname at and bt:\n";
    for(int i=0;i<n;i++){
        string pname;
        double at,bt;

        cin>>pname>>at>>bt;
        p.push_back(Process(pname,at,bt));
    }
    schedule(p,avg_tat,avg_wt,g);
    displayGantt(g);
    displayTable(p,avg_tat,avg_wt);
    return 0;
}