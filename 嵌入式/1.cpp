void InitInt (void) {
#define V 0 // 0 = 向量模式， 1 = 非向量模式
#define I 0 // 0 = IRQ 使能
#define F 1 // 0 = FIQ 使能 (不允许向量中断) ，1 = Reserved
rINTCON = ((V<<2)+(I<<1)+F);
// EINT0 EINT1 EINT2 EINT3 EINT4567 TICK ZDMA0 ZDMA1 
// BDMA0 BDMA1 WDT UERR0/1 TIMER0 TIMER1 TIMER2
// TIMER3 TIMER4 TIMER5 URXD0 URXD1 IIC SIO UTXD0 UTXD1 
// RTC ADC.
// 0 = IRQ mode，1 = FIQ mode
rINTMOD = 
((0<<25)+(0<<24)+(0<<23)+(0<<22)+(0<<21)+(0<<20)+(0<<19)+(0<<18
)+(0<<17)+(0<<16)+(0<<15)+(0<<14)+(0<<13)+(0<<12)+(0<<11)+(0<<1
0)+(0<<9)+(0<<8)+(0<<7)+(0<<6)+(0<<5)+(0<<4)+(0<<3)+(0<<2)+(0<<
1)+0);
// 0 = Service available 1 = Masked
rINTMSK = 
((0<<25)+(1<<24)+(1<<23)+(1<<22)+(1<<21)+(1<<20)+(1<<19)+(1<<18
)+(1<<17)+(1<<16)+(0<<15)+(1<<14)+(0<<13)+(1<<12)+(1<<11)+(1<<1
0)+(1<<9)+(1<<8)+(1<<7)+(1<<6)+(1<<5)+(1<<4)+(1<<3)+(1<<2)+(1<<
1)+1);
// IRQ priority of slave register
rI_PSLV = 0x1b1b1b1b; //默认值
// IRQ priority of master register //默认值
rI_PMST = 0x00001f1b;
// IRQ interrupt service pending clear register
rI_ISPC = rI_ISPR; // Clear all pending.
// FIQ interrupt service pending clear register
rF_ISPC = rF_ISPR; // Clear all pending.
rEXTINTPND = 0x0f; // 外部中断(4,5,6,and 7) 悬挂寄存器被置1清除
}