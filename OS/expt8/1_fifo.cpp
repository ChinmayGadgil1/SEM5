#include<iostream>
using namespace std;

int main(){
    int frames, pages, page_faults = 0;
    
    cout << "Enter the number of Frames: ";
    cin >> frames;
    
    cout << "Enter the number of Pages: ";
    cin >> pages;
    
    int page_sequence[pages];
    int frame_array[frames];
    bool is_fault[pages];
    int frame_status[pages][frames];
    
    // Initialize frames to -1 (empty)
    for(int i = 0; i < frames; i++){
        frame_array[i] = -1;
    }
    
    cout << "Enter the Page sequence: ";
    for(int i = 0; i < pages; i++){
        cin >> page_sequence[i];
    }
    
    int next_replace = 0; // FIFO pointer
    
    // FIFO Algorithm
    for(int i = 0; i < pages; i++){
        bool found = false;
        
        // Check if page already exists in frames
        for(int j = 0; j < frames; j++){
            if(frame_array[j] == page_sequence[i]){
                found = true;
                break;
            }
        }
        
        if(!found){
            // Page fault occurs
            frame_array[next_replace] = page_sequence[i];
            next_replace = (next_replace + 1) % frames;
            page_faults++;
            is_fault[i] = true;
        } else {
            is_fault[i] = false;
        }
        
        // Store current frame status
        for(int j = 0; j < frames; j++){
            frame_status[i][j] = frame_array[j];
        }
    }
    
    // Display output
    cout << "\n\t\tOUTPUT" << endl;
    cout << "Enter the number of Frames: " << frames << endl;
    cout << "Enter the number of Pages: " << pages << endl;
    cout << "Enter the Page sequence: ";
    for(int i = 0; i < pages; i++){
        cout << page_sequence[i] << " ";
    }
    cout << endl << endl;
    
    cout << "Page\t[";
    for(int i = 0; i < frames; i++){
        cout << "F" << (i+1);
        if(i < frames-1) cout << " ";
    }
    cout << "]\tHit/Fault" << endl;
    
    for(int i = 0; i < pages; i++){
        cout << page_sequence[i] << "\t[";
        for(int j = 0; j < frames; j++){
            if(frame_status[i][j] == -1)
                cout << "  ";
            else
                cout << frame_status[i][j] << " ";
        }
        cout << "]\t";
        if(is_fault[i])
            cout << "Fault" << endl;
        else
            cout << "Hit" << endl;
    }
    
    cout << "\nTotal Page Faults: " << page_faults << endl;
    cout << "Total Page Hits: " << (pages - page_faults) << endl;
    
    return 0;
}