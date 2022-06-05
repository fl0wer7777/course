#include <iostream>
#include <vector>
#include <cstring>
#include <fstream>
using namespace std;

vector<string> key = { "do", "end", "for", "if", "printf", "scanf", "then", "while" };
vector<string> delimiter = { ",", ";", "(", ")", "{", "}" };
vector<string> arithmeticOperation = { "+", "-", "*", "/" };
vector<string> relationalOperation = { "<", "<=", "=", ">", ">=", "<>" };

bool isKey(string s);
bool isLetter(char c);
bool isDigit(char c);
bool isDelimiter(string s);
bool isArithmeticOperation(string s);
bool isRelationalOperation(string s);
void lex(string file);

int main() {
	string file = "C://Users//dell//Desktop//1.txt";
	lex(file);

	return 0;
}

bool isKey(string s) {
	for (int i = 0; i < key.size(); i++) {
		if (key[i] == s) {
			return true;
		}
	}
	return false;
}

bool isLetter(char c) {
	if (((c >= 'a') && (c <= 'z')) || ((c >= 'A') && (c <= 'Z'))) {
		return true;
	}
	return false;
}

bool isDigit(char c) {
	if (c >= '0' && c <= '9') {
		return true;
	}
	return false;
}

bool isDelimiter(string s) {
	for (int i = 0; i < delimiter.size(); i++) {
		if (delimiter[i] == s) {
			return true;
		}
	}
	return false;
}

bool isArithmeticOperation(string s) {
	for (int i = 0; i < arithmeticOperation.size(); i++) {
		if (arithmeticOperation[i] == s) {
			return true;
		}
	}
	return false;
}

bool isRelationalOperation(string s) {
	for (int i = 0; i < relationalOperation.size(); i++) {
		if (relationalOperation[i] == s) {
			return true;
		}
	}
	return false;
}

void lex(string file) {
	ifstream input;
	input.open(file, ios::in);
	if (!input.is_open()) {
		cout << "Error!" << endl;
		return;
	}

	string s = "";
	char c;
	int line = 1, row = 0;
	while ((input.get(c))) {
		s = "";
		if ((c == ' ') || (c == '\t')) {}
		else if (c == '\n') {
			line++;
			row = 0;
		}
		else if (isLetter(c)) {
			row++;
			while (isLetter(c) || isDigit(c) || c == '_') {
				s += c;
				input.get(c);
			}
			input.seekg(-1, ios::cur);
			if (isKey(s)) {
				cout << s << "\t(" << 1 << "," << s << ")" << "\t关键字" << "\t(" << line << "," << row << ")" << endl;
			}
			else {
				cout << s << "\t(" << 6 << "," << s << ")" << "\t标识符" << "\t(" << line << "," << row << ")" << endl;
			}
		}
		else if (isDigit(c)) {
			row++;
			while (isDigit(c)) {
				s += c;
				input.get(c);
			}
			if (isLetter(c)) {
				while (isLetter(c)) {
					s += c;
					input.get(c);
				}
				input.seekg(-1, ios::cur);
				cout << s << "\t(" << 0 << "," << s << ")" << "\tError" << "\t(" << line << "," << row << ")" << endl;
				continue;
			}
			input.seekg(-1, ios::cur);
			cout << s << "\t(" << 5 << "," << s << ")" << "\t常数" << "\t(" << line << "," << row << ")" << endl;
		}
		else {
			s = "";
			s += c;
			if (isArithmeticOperation(s)) {
				row++;
				s = "";
				if (c == '+') {
					s += c;
					input.get(c);
					if (c == '+') {
						s += c;
						cout << s << "\t(" << 3 << "," << s << ")" << "\t算术运算符" << "\t(" << line << "," << row << ")" << endl;
					}
					else {
						input.seekg(-1, ios::cur);
						s = "+";
						cout << s << "\t(" << 3 << "," << s << ")" << "\t算术运算符" << "\t(" << line << "," << row << ")" << endl;
					}
				}
				else if (c == '-') {
					s += c;
					input.get(c);
					if (c == '-') {
						s += c;
						cout << s << "\t(" << 3 << "," << s << ")" << "\t算术运算符" << "\t(" << line << "," << row << ")" << endl;
					}
					else {
						input.seekg(-1, ios::cur);
						s = "+";
						cout << s << "\t(" << 3 << "," << s << ")" << "\t算术运算符" << "\t(" << line << "," << row << ")" << endl;
					}
				}
				else {
					cout << s << "\t(" << 3 << "," << s << ")" << "\t算术运算符" << "\t(" << line << "," << row << ")" << endl;
				}
			}
			else if (isDelimiter(s)) {
				row++;
				cout << s << "\t(" << 2 << "," << s << ")" << "\t分界符" << "\t(" << line << "," << row << ")" << endl;
			}
			else if (isRelationalOperation(s)) {
				switch (c) {
					row++;
				case '=': {
					row++;
					input.get(c);
					s += c;
					if (isRelationalOperation(s)) {
						cout << s << "\t(" << 4 << "," << s << ")" << "\t关系运算符" << "\t(" << line << "," << row << ")" << endl;
					}
					else {
						s.pop_back();
						cout << s << "\t(" << 4 << "," << s << ")" << "\t关系运算符" << "\t(" << line << "," << row << ")" << endl;
						input.seekg(-1, ios::cur);
					}
				}
					break;
				case '>': {
					row++;
					input.get(c);
					s += c;
					if (isRelationalOperation(s)) {
						cout << s << "\t(" << 4 << "," << s << ")" << "\t关系运算符" << "\t(" << line << "," << row << ")" << endl;
					}
					else {
						s.pop_back();
						cout << s << "\t(" << 4 << "," << s << ")" << "\t关系运算符" << "\t(" << line << "," << row << ")" << endl;
						input.seekg(-1, ios::cur);
					}
				}
					break;
				case '<': {
					row++;
					input.get(c);
					s += c;
					if (isRelationalOperation(s)) {
						cout << s << "\t(" << 4 << "," << s << ")" << "\t关系运算符" << "\t(" << line << "," << row << ")" << endl;
					}
					else {
						s.pop_back();
						cout << s << "\t(" << 4 << "," << s << ")" << "\t关系运算符" << "\t(" << line << "," << row << ")" << endl;
						input.seekg(-1, ios::cur);
					}
				}
					break;
				default: {
					row++;
					cout << c << "\t(" << 0 << "," << s << ")" << "\tError" << "\t(" << line << "," << row << ")" << endl;
				}
					break;
				}
			}
			else {
				row++;
				cout << c << "\t(" << 0 << "," << s << ")" << "\tError" << "\t(" << line << "," << row << ")" << endl;
			}
		}
	}
	input.close();
}