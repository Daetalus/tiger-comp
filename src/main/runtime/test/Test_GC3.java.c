// This is automatically generated by the Tiger compiler.
// Do NOT modify!

extern void *previous;
extern void *Tiger_new_array (int);
extern void *Tiger_new (void *, int);
extern int System_out_println (int);
// structures
struct Test_GC3
{
  struct Test_GC3_vtable *vptr;
  int isObjOrArray;
  int length;
  void* forwarding;
};
struct Garbage
{
  struct Garbage_vtable *vptr;
  int isObjOrArray;
  int length;
  void* forwarding;
  struct GC * g1;
  struct GC * g2;
};
struct Doit
{
  struct Doit_vtable *vptr;
  int isObjOrArray;
  int length;
  void* forwarding;
};
// vtables structures
struct Test_GC3_vtable
{
  char* Test_GC3_gc_map;
};

struct Garbage_vtable
{
  char* Garbage_gc_map;
};

struct Doit_vtable
{
  char* Doit_gc_map;
  int (*doit)(struct Doit * this,int n);
  int (*doit2)(struct Doit * this,struct Garbage * g1,struct Garbage * g2,struct Garbage * g3);
};


//methods decl
int Doit_doit(struct Doit * this, int n);
int Doit_doit2(struct Doit * this, struct Garbage * g1, struct Garbage * g2, struct Garbage * g3);
// vtables
struct Test_GC3_vtable Test_GC3_vtable_ = 
{
  "",
};

struct Garbage_vtable Garbage_vtable_ = 
{
  "11",
};

struct Doit_vtable Doit_vtable_ = 
{
  "",
  Doit_doit,
  Doit_doit2,
};


//GC stack frames
struct Tiger_main_gc_frame
{
    void *prev_;
    char *arguments_gc_map;
    int *arguments_base_address;
    int locals_gc_map;
    struct Doit * x_0;
};

struct Doit_doit_gc_frame
{
    void *prev_;
    char *arguments_gc_map;
    int *arguments_base_address;
    int locals_gc_map;
    int*  array;
    struct Doit * d1;
    struct Garbage * g1;
    struct Garbage * g2;
    struct Garbage * g3;
    struct Doit * x_1;
};

struct Doit_doit2_gc_frame
{
    void *prev_;
    char *arguments_gc_map;
    int *arguments_base_address;
    int locals_gc_map;
};

// memory GC maps
int Tiger_main_locals_gc_map = 1;

char* Doit_doit_arguments_gc_map="10";
int Doit_doit_locals_gc_map=6;

char* Doit_doit2_arguments_gc_map="1111";
int Doit_doit2_locals_gc_map=0;

// methods
int Doit_doit(struct Doit * this, int n)
{
  struct Doit_doit_gc_frame frame;
  frame.prev_=previous;
  previous=&frame;
  frame.arguments_gc_map = Doit_doit_arguments_gc_map;
  frame.arguments_base_address = (int*)&this;
  frame.locals_gc_map = Doit_doit_locals_gc_map;
  int i;
  int j;
  frame.array=0;
  frame.d1=0;
  frame.g1=0;
  frame.g2=0;
  frame.g3=0;
  frame.x_1=0;

  frame.g1 = ((struct Garbage*)(Tiger_new (&Garbage_vtable_, sizeof(struct Garbage))));
  frame.g2 = ((struct Garbage*)(Tiger_new (&Garbage_vtable_, sizeof(struct Garbage))));
  frame.g3 = ((struct Garbage*)(Tiger_new (&Garbage_vtable_, sizeof(struct Garbage))));
  i = 0;
  while (i < n)
    {
      System_out_println (i);
      frame.array = (int*)Tiger_new_array(5);
      frame.array[0+6] = 0;
      frame.array[1+6] = 2;
      System_out_println (frame.array[3]);
      frame.d1 = ((struct Doit*)(Tiger_new (&Doit_vtable_, sizeof(struct Doit))));
      j = (frame.x_1=frame.d1, frame.x_1->vptr->doit2(frame.x_1, frame.g1, frame.g2, frame.g3));
      i = i + 1;
    }
  
  previous=frame.prev_;
  return i;
}
int Doit_doit2(struct Doit * this, struct Garbage * g1, struct Garbage * g2, struct Garbage * g3)
{
  struct Doit_doit2_gc_frame frame;
  frame.prev_=previous;
  previous=&frame;
  frame.arguments_gc_map = Doit_doit2_arguments_gc_map;
  frame.arguments_base_address = (int*)&this;
  frame.locals_gc_map = Doit_doit2_locals_gc_map;
  int i;

  g1 = ((struct Garbage*)(Tiger_new (&Garbage_vtable_, sizeof(struct Garbage))));
  g2 = ((struct Garbage*)(Tiger_new (&Garbage_vtable_, sizeof(struct Garbage))));
  g3 = ((struct Garbage*)(Tiger_new (&Garbage_vtable_, sizeof(struct Garbage))));
  i = 10;

  previous=frame.prev_;
  return i;
}

// main method
int Tiger_main ()
{
    struct Tiger_main_gc_frame frame;
    frame.prev_=previous;
    previous=&frame;
    frame.arguments_gc_map = 0;
    frame.arguments_base_address = 0;
    frame.locals_gc_map = Tiger_main_locals_gc_map;
    frame.x_0=0;
    System_out_println ((frame.x_0=((struct Doit*)(Tiger_new (&Doit_vtable_, sizeof(struct Doit)))), frame.x_0->vptr->doit(frame.x_0, 101)));
    return 0;
}




