//!FINAL VERSION

#include <iostream>
#include <iomanip>
#include <vector>
#include <cstdlib>

using namespace std;

int m, n;
vector<vector<int>> Allocation, Max, Need;
vector<int> Available;

int safety() {
    vector<int> safeSequence(n, -1), Work = Available;
    vector<bool> Finish(n, false);

    int k = 0;
    bool found;

    while (true) {
        found = false;

        for (int i = 0; i < n; i++) {
            if (!Finish[i]) {
                bool canAllocate = true;
                for (int j = 0; j < m; j++) {
                    if (Need[i][j] > Work[j]) {
                        canAllocate = false;
                        break;
                    }
                }

                if (canAllocate) {
                    for (int j = 0; j < m; j++)
                        Work[j] += Allocation[i][j];
                    Finish[i] = true;
                    safeSequence[k++] = i;
                    found = true;
                }
            }
        }

        if (!found)
            break;
    }

    for (int i = 0; i < n; i++) {
        if (!Finish[i]) {
            cout << "\nUnsafe state.\n";
            return 1;  // unsafe
        }
    }

    cout << "\nSafe state. Sequence: <";
    for (int j = 0; j < n; j++)
        cout << "P" << safeSequence[j] << (j < n - 1 ? ", " : ">\n");

    return 0; // safe
}

void display() {
    cout << "\n\tAlloc\tMax\tAvail\tNeed\n";

    int printedAvailable = 0;

    for (int i = 0; i < n; i++) {
        cout << "P" << i << "\t";

        for (int j = 0; j < m; j++)
            cout << Allocation[i][j] << " ";
        cout << "\t";

        for (int j = 0; j < m; j++)
            cout << Max[i][j] << " ";
        cout << "\t";

        if (!printedAvailable) {
            for (int j = 0; j < m; j++)
                cout << Available[j] << " ";
            printedAvailable = 1;
        }
        cout << "\t";

        for (int j = 0; j < m; j++)
            cout << Need[i][j] << " ";
        cout << "\n";
    }
}

int main() {
    cout << "Processes: ";
    cin >> n;
    cout << "Resources: ";
    cin >> m;

    Allocation.assign(n, vector<int>(m, 0));
    Max.assign(n, vector<int>(m, 0));
    Need.assign(n, vector<int>(m, 0));
    Available.assign(m, 0);

    cout << "\nAllocated:\n";
    for (int i = 0; i < n; i++) {
        cout << "P" << i << ": ";
        for (int j = 0; j < m; j++)
            cin >> Allocation[i][j];
    }

    cout << "\nMax:\n";
    for (int i = 0; i < n; i++) {
        cout << "P" << i << ": ";
        for (int j = 0; j < m; j++)
            cin >> Max[i][j];
    }

    cout << "\nAvailable: ";
    for (int i = 0; i < m; i++)
        cin >> Available[i];

    for (int i = 0; i < n; i++)
        for (int j = 0; j < m; j++)
            Need[i][j] = Max[i][j] - Allocation[i][j];

    display();
    int stateFlag = safety();

    char choice = 'y';
    do {
        vector<int> Request(m);
        int p;

        cout << "\nProcess: ";
        cin >> p;

        cout << "Request: ";
        for (int i = 0; i < m; i++)
            cin >> Request[i];

        bool allZeroes = true;
        for (int i = 0; i < m; i++) {
            if (Available[i] - Request[i]) {
                allZeroes = false;
                break;
            }
        }

        if (allZeroes) {
            cout << "\nCannot grant - sets available to zero.\n";
            cout << "Try another? (Y/N): ";
            cin >> choice;
            continue;
        }

        bool invalid = false;
        for (int i = 0; i < m; i++) {
            if (Request[i] > Need[p][i]) {
                cout << "\nExceeded max claim.\n";
                invalid = true;
                break;
            } else if (Request[i] > Available[i]) {
                cout << "\nResources unavailable.\n";
                invalid = true;
                break;
            }
        }

        if (invalid) {
            cout << "Try another? (Y/N): ";
            cin >> choice;
            continue;
        }

        for (int i = 0; i < m; i++) {
            Available[i] -= Request[i];
            Allocation[p][i] += Request[i];
            Need[p][i] -= Request[i];
        }

        cout << "\nTemp state:\n";
        cout << "\n\tAlloc\tAvail\tNeed\n";

        int printedAvailable = 0;

        for (int i = 0; i < n; i++) {
            cout << "P" << i << "\t";

            for (int j = 0; j < m; j++)
                cout << Allocation[i][j] << " ";
            cout << "\t";

            if (!printedAvailable) {
                for (int j = 0; j < m; j++)
                    cout << Available[j] << " ";
                printedAvailable = 1;
            }
            cout << "\t";

            for (int j = 0; j < m; j++)
                cout << Need[i][j] << " ";
            cout << "\n";
        }
        stateFlag = safety();

        if (stateFlag) {
            cout << "\nUnsafe. Restoring.\n";
            for (int i = 0; i < m; i++) {
                Available[i] += Request[i];
                Allocation[p][i] -= Request[i];
                Need[p][i] += Request[i];
            }
            cout << "\nRestored:\n";
            display();
        } else {
            cout << "Granted.\n";
            display();
        }

        cout << "Try another? (Y/N): ";
        cin >> choice;

    } while (choice == 'y' || choice == 'Y');

    return 0;
}