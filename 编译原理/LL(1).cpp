#include <iostream>
#include <string>
#include <vector>
#include <stack>
#include <unordered_map>
#include <unordered_set>
#include <algorithm>
#include <fstream>
using namespace std;

void printStack(stack<char> st, string s) {
		char c;
		string ss;
		stack<char> temp;
		while (!st.empty()) {
			c = st.top();
			st.pop();
			temp.push(c);
			ss += c;
		}
		while (!temp.empty()) {
			c = temp.top();
			temp.pop();
			st.push(c);
		}
		reverse(ss.begin(), ss.end());
		cout << ss << "\t" << s << endl;
}

int main() {
	string input;
	stack<char> st;
	unordered_map<char, unordered_set<char>> FIRST;
	unordered_map<char, unordered_set<char>> FOLLOW;
	unordered_map<char, unordered_map<char, string>> M;

	unordered_set<char> FIRST0 = {};
	unordered_set<char> FOLLOW0 = { '#' };
	unordered_set<char> FOLLOW1 = {};
	unordered_map<char, unordered_map<char, string>> t1;
	unordered_map<char, string> M0 = {};
	FIRST['F'] = FIRST0;
	FIRST['S'] = FIRST0;
	FIRST['T'] = FIRST0;
	FIRST['G'] = FIRST0;
	FIRST['E'] = FIRST0;
	FOLLOW['F'] = FOLLOW1;
	FOLLOW['S'] = FOLLOW1;
	FOLLOW['T'] = FOLLOW1;
	FOLLOW['G'] = FOLLOW1;
	FOLLOW['E'] = FOLLOW0;
	M['F'] = M0;
	M['S'] = M0;
	M['T'] = M0;
	M['G'] = M0;
	M['E'] = M0;

	vector<vector<string>> grammar = { {"F", "(E)", "i"}, {"S", "e", "*FS", "/FS"}, {"T", "FS"}, {"G", "e", "+TG", "-TG"}, {"E", "TG"} };
	for (int i = 0; i < grammar.size(); i++) {
		vector<string> array = grammar[i];
		char left = array[0][0];
		for (int j = 1; j < array.size(); j++) {
			string s = array[j];
			if (s[0] >= 'A' && s[0] <= 'Z') {
				for (int k = 0; (k < s.size()) && (s[k] >= 'A' && s[k] <= 'Z'); k++) {
					for (unordered_set<char>::iterator it = FIRST[s[k]].begin(); it != FIRST[s[k]].end(); ++it) {
						FIRST[left].insert(*it);
					}
					bool flag = 0;
					for (int m = 0; m < s.size(); m++) {
						if (s[m] == 'e') {
							flag = 1;
						}
					}
					if (!flag) {
						break;
					}
					if (k == s.size() - 1) {
						FIRST[left].insert('e');
					}
				}
			}
			else {
				FIRST[left].insert(s[0]);
			}
		}
	}
	
	grammar = { {"F", "(E)", "i"}, {"E", "TG"}, {"G", "e", "+TG", "-TG"}, {"S", "e", "*FS", "/FS"}, {"T", "FS"} };
	for (int i = 0; i < grammar.size(); i++) {
		vector<string> array = grammar[i];
		char left = array[0][0];
		for (int j = 1; j < array.size(); j++) {
			string s = array[j];
			for (int k = 0; k < s.size(); k++) {
				char c = s[k];
				if (c >= 'A' && c <= 'Z') {
					if (k == s.size() - 1) {
						for (unordered_set<char>::iterator it = FOLLOW[left].begin(); it != FOLLOW[left].end(); ++it) {
							FOLLOW[c].insert(*it);
						}
						break;
					}
				}
				unordered_set<char> temp = {};
				for (int m = k + 1; m < s.size(); m++) {
					if (s[m] >= 'A' && s[m] <= 'Z') {
						for (unordered_set<char>::iterator it = FIRST[s[m]].begin(); it != FIRST[s[m]].end(); ++it) {
							temp.insert(*it);
						}
					}
					else {
						temp.insert(s[m]);
						break;
					}
					if (FIRST[s[m]].count('e') == 0) {
						break;
					}
					if (m == s.size() - 1) {
						for (unordered_set<char>::iterator it = FOLLOW[left].begin(); it != FOLLOW[left].end(); ++it) {
							FOLLOW[c].insert(*it);
						}
					}
				}
				temp.erase('e');
				for (unordered_set<char>::iterator it = temp.begin(); it != temp.end(); ++it) {
					FOLLOW[c].insert(*it);
				}
			}
		}
	}

	for (int i = 0; i < grammar.size(); i++) {
		vector<string> array = grammar[i];
		char left = array[0][0];
		for (int j = 1; j < array.size(); j++) {
			string s = array[j];
			if (s[0] >= 'A' && s[0] <= 'Z') {
				unordered_set<char> temp = {};
				for (int k = 0; k < s.size(); k++) {
					for (unordered_set<char>::iterator it = FIRST[s[k]].begin(); it != FIRST[s[k]].end(); ++it) {
						temp.insert(*it);
					}
					if (FIRST[s[k]].count('e') == 0) {
						temp.erase('e');
						break;
					}
				}
				for (unordered_set<char>::iterator it = temp.begin(); it != temp.end(); ++it) {
					if (*it != 'e') {
						M[left][*it] = s;
					}
				}
				for (unordered_set<char>::iterator it = temp.begin(); it != temp.end(); ++it) {
					if (*it == 'e') {
						for (unordered_set<char>::iterator it0 = FOLLOW[left].begin(); it0 != FOLLOW[left].end(); ++it0) {
							M[left][*it0] = "e";
						}
						break;
					}
				}
			}
			else if (s[0] == 'e') {
				for (unordered_set<char>::iterator it = FOLLOW[left].begin(); it != FOLLOW[left].end(); ++it) {
						M[left][*it] = "e";
				}
			}
			else {
				M[left][s[0]] = s;
			}
		}
	}


	input = "i+i*i#";

	st.push('#');
	st.push('E');
	
	printStack(st, input);

	char x = st.top();
	st.pop();
	char a = input[0];

	while (true) {
		if (x == '#' && a == '#') {
			cout << "Success!" << endl;
			break;
		}
		else if (x != a) {
			string s;
			if (M[x].empty()) {
				s = "";
				cout << "Error!" << endl;
				break;
			}
			else {
				s = M[x][a];
				if (s != "e") {
					reverse(s.begin(), s.end());
					for (int i = 0; i < s.size(); i++) {
						st.push(s[i]);
					}
				}
				printStack(st, input);
				x = st.top();
				st.pop();
			}
		}
		else {
			input.erase(0, 1);
			a = input[0];
			printStack(st, input);
			x = st.top();
			st.pop();
		}
	}
}