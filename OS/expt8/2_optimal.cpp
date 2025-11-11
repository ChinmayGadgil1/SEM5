#include<iostream>
using namespace std;

int predict(int page_sequence[], int frame_array[], int pages, int frames, int current_index){
    int farthest = current_index;
    int replace_index = -1;
    
    for(int i = 0; i < frames; i++){
        int j;
        for(j = current_index; j < pages; j++){
            if(frame_array[i] == page_sequence[j]){
                if(j > farthest){
                    farthest = j;
                    replace_index = i;
                }
                break;
            }
        }
        
        // If page is never referenced again
        if(j == pages){
            return i;
        }
    }
    
    return (replace_index == -1) ? 0 : replace_index;
}

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
    
    int filled = 0;
    
    // Optimal Algorithm
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
            if(filled < frames){
                frame_array[filled] = page_sequence[i];
                filled++;
            } else {
                int replace_index = predict(page_sequence, frame_array, pages, frames, i + 1);
                frame_array[replace_index] = page_sequence[i];
            }
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