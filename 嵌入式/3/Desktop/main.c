/************************************************
 *
 *	The classic producer-consumer example.
 * 	Illustrates mutexes and conditions.
 *  by Zou jian guo <ah_zou@tom.com>   
 *  2003-12-22
 *
*************************************************/

#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include "pthread.h"
#include "kblistener.h"

#define BUFFER_SIZE 16
#define TOTALCOUNT 10000

/* Circular buffer of integers. */
struct prodcons {
  int buffer[BUFFER_SIZE];      /* the actual data */
  pthread_mutex_t lock;         /* mutex ensuring exclusive access to buffer */
  int readpos, writepos;        /* positions for reading and writing */
  pthread_cond_t notempty;      /* signaled when buffer is not empty */
  pthread_cond_t notfull;       /* signaled when buffer is not full */
};

/*--------------------------------------------------------*/
/* Initialize a buffer */
void init(struct prodcons * b)
{
  pthread_mutex_init(&b->lock, NULL);
  pthread_cond_init(&b->notempty, NULL);
  pthread_cond_init(&b->notfull, NULL);
  b->readpos = 0;
  b->writepos = 0;
}
/*--------------------------------------------------------*/
/* Store an integer in the buffer */
void put(struct prodcons * b, int data)
{
	pthread_mutex_lock(&b->lock);

  	/* Wait until buffer is not full */
 	while ((b->writepos + 1) % BUFFER_SIZE == b->readpos) {
		printf("wait for not full\n");
    	pthread_cond_wait(&b->notfull, &b->lock);
  	}
  /* Write the data and advance write pointer */
  	b->buffer[b->writepos] = data;
  	b->writepos++;
  	if (b->writepos >= BUFFER_SIZE) b->writepos = 0;
  /* Signal that the buffer is now not empty */
  	pthread_cond_signal(&b->notempty);

	pthread_mutex_unlock(&b->lock);
}
/*--------------------------------------------------------*/
/* Read and remove an integer from the buffer */
int get(struct prodcons * b)
{
  	int data;
	pthread_mutex_lock(&b->lock);

 	/* Wait until buffer is not empty */
  	while (b->writepos == b->readpos) {
    	printf("wait for not empty\n");
		pthread_cond_wait(&b->notempty, &b->lock);
  	}
  	/* Read the data and advance read pointer */
  	data = b->buffer[b->readpos];
  	b->readpos++;
  	if (b->readpos >= BUFFER_SIZE) b->readpos = 0;
  	/* Signal that the buffer is now not full */
  	pthread_cond_signal(&b->notfull);

  	pthread_mutex_unlock(&b->lock);
  	return data;
}
/*--------------------------------------------------------*/
#define OVER (-1)
struct prodcons buffer;
/*--------------------------------------------------------*/
int n;
void * producer(void * data)
{
  	for (n = 0; n < TOTALCOUNT; n++) {
    	printf(" put-->%d\n", n);
    	put(&buffer, n);
	}
  put(&buffer, OVER);
  printf("producer stopped!\n");
  return NULL;
}
/*--------------------------------------------------------*/
void * consumer(void * data)
{
  int d;
  while (1) {
    d = get(&buffer);
    if (d == OVER ) break;
    printf("              %d-->get\n", d);
  }
  printf("consumer stopped!\n");
  return NULL;
}
/*--------------------------------------------------------*/
void * listener() {
    
    init_kblistener();

    while (1) {
        kbhit();
        if (getch() == 0x1B) {
            printf("Esc now!\n");
            exit(EXIT_SUCCESS);
        }

		if (n ==TOTALCOUNT){
			exit(EXIT_SUCCESS);
		}
    }

    close_kblistener();
}
/*--------------------------------------------------------*/
int main(void)
{
  	pthread_t th_a, th_b, th_c;
  	void * retval;

  	init(&buffer);
 	pthread_create(&th_a, NULL, producer, 0);
  	pthread_create(&th_b, NULL, consumer, 0);
    pthread_create(&th_c, NULL, listener, 0);
    
  /* Wait until producer and consumer finish. */
  	pthread_join(th_a, &retval);
  	pthread_join(th_b, &retval);
    pthread_join(th_c, &retval);

  	return 0;
}
