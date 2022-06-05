unsigned char ReadKeyBoard(void) {
    unsigned int PCONFback = 0;
    unsigned int PUPFback = 0;
    const unsigned char scanvalue[16] = {0xEE, 0xDE, 0xBE, 0x7E, 0xED, 0xDD, 0xBD, 0x7D, 0xEB, 0xDB, 0xBB, 0x7B, 0xE7, 0xD7, 0xB7, 0x77};
    const unsigned char KeyAscCode[16] = {00, 01, 02, 03, 10, 11, 12, 13, 20, 21, 22, 23, 30, 31, 32, 33};
    unsigned int a, i, key, temp, temp1, temp2, temp3;
    PCONFback = rPCONF;
    PUPFback = rPUPF;
    rPUPF = 0x00;
    rPCONF = 0x0055;
    rPDATF = 0xF0;
    temp1 = rPDATF & 0xF0;
    rPCONF = 0x1250;
    rPDATF = 0x0F;
    temp2 = rPDATF & 0x0F;
    temp = temp1 | temp2;
    if (temp != 0xFF) {
        for (a = 255; a > 0; a--) {}
        rPCONF = 0x0055;
        rPDATF = 0xF0;
        temp1 = rPDATF & 0xF0;
        rPCONF = 0x1250;
        rPDATF = 0x0F;
        temp2 = rPDATF & 0x0F;
        temp3 = temp1 | temp2;
        if (temp3 == temp) {
        	for (key = 0; key < 16; key++) {
        		if (temp == scanvalue[key]) {
                    rPCONF = PCONFback;
                    rPUPF = PUPFback;
                    rPDATF = 0xFF;
                    return(KeyAscCode[key]);
                }
                else {
                    rPCONF = PCONFback;
                    rPUPF = PUPFback;
                    return(0xEE);
                }
			}
		}
    }
    return(0xFF);
}
