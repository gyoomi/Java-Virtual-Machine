## 三、垃圾回收器和内存分配策略
### 3.1 概述
1. 那些内存需要回收？什么时候进行回收？如何进行回收？
2. 程序计数器、虚拟机栈、本地方法栈这三部分随着线程而生，随着线程而灭。栈中的栈帧随着方法有序的进出。每一个栈帧中分配内存是在类结构确定下来就已知（JIT会在编译时进行些许优化）
3. 因此程序计数器、虚拟机栈、本地方法栈这三者内存分配和回收都具备确定性，垃圾回收再这几块不需要你过多的考虑。他们会随着线程的回收而自动进行回收。
4. Java堆和方法区（Java堆中一个逻辑部分），所以说垃圾回收也是主要考虑到这块的内存的回收和利用。
### 3.2 对象已死？
1. 需要判断Java堆中有哪些对象是活着或者死去（即不可能被任何途径使用的对象）
#### 3.2.1 引用计数算法
1. 引用计数算法很优秀应用很广泛，但是它很难解决对象之间循环依赖的而导致的问题
2. 引用计数算法的存在的缺陷，如上1
3. JavaVM不是通过引用计数来进行垃圾回收的（来判断对象是否存活）
#### 3.2.2 根搜索算法（GC Roots Tracing）
1. Java中可以作为GC Roots对象包括以下几种：
   - 虚拟机栈（栈帧中本地变量表）中引用的对象；
   - 方法区中类的静态属性引用的对象；
   - 方法区中常量引用的对象；
   - 本地方法栈中JNI（一般只Native方法）的引用对象
2. 示例如下
   ![](./asserts/001.png)
#### 3.2.3 再谈引用
1. 1.2之前Java中对象只有：引用和未引用两种状态
2. 1.2之后进行了扩充：强引用（Strong Reference）、软引用（Soft Reference）、弱引用（Weak Reference）、虚引用（Phantom Reference）
   - Strong Reference：只要强引用还存在，垃圾回收器就不会进行回收
   - Soft Reference：一些还有用，但是非必需的对象。系统将要发生OOM时，会将这些对象列入回收范围，并进行第二次垃圾回收。如果回收之后内存还不够则会抛出OOM。Java中提供SoftReference来实现。
   - Weak Reference：描述非必需对象。弱引用的关联的对象只能生存到下一次垃圾回收器发生之前。无论内存是否充足，都会回收掉弱引用关联的对象。WeakReference类
   - Phantom Reference：最弱的一种引用。一个对象是否有虚引用的存在都不会对其生存时间造成影响，也无法通过虚引用来获取一个对象的实例。为一个对象设置、
     虚引用的唯一目的就是希望在该对象被垃圾回收器回收时收到一个系统通知。PhantomReference类。
#### 3.2.4 生存还是死亡
1. 根搜索法不可达的对象，还有两次标记的过程，进行自救。
2. 过程：
   在跟搜索算法不可达的对象，并将第一次被标记并且进行一次筛选。筛选条件是：此对象是否有必要调用finalize()方法。当对象没有覆盖finalize或方法已被虚拟机执行了，虚拟机
   会认为以上两种情况没有必要执行。  
   如果这个对象被判定为有必要执行finalize()方法。该对象将会被F-Queue的队列中，稍后虚拟机将建立一个低优先级的Finalizer线程去执行。这里的执行指的是虚拟机会触发该  
   方法，但是并不承诺等待他运行结束（原因：finalizer执行很慢或死循环，导致队列中其他的对象永远在等待或内存溢出）。finalizer方法是对象逃离死亡的最后一次机会，对象只要finalizer中  
   拯救自己（建立自己引用）第二次标记的时候该对象就被移除回收队列。如果没有拯救，那么很快不久就被回收。但是如果对象的finalizer方法执行了，但是可能该对象还存活着。
3. 实例代码如下：
   ```
       public static FinalizerEscapeGC SAVE_HOOK = null;
   
       public void isAlive() {
           System.out.println("yes, i am still alive!!!");
       }
   
       @Override
       protected void finalize() throws Throwable {
           super.finalize();
           System.out.println("finalizer method execute");
           FinalizerEscapeGC.SAVE_HOOK = this;
       }
   
       public static void main(String[] args) throws Exception {
           SAVE_HOOK = new FinalizerEscapeGC();
           // 对象第一次进行成功的拯救
           SAVE_HOOK = null;
           System.gc();
           // 因为finalizer方法的优先级很低，所以暂停0.5s,以等待他运行
           Thread.sleep(500);
           if (SAVE_HOOK != null) {
               SAVE_HOOK.isAlive();
           } else {
               System.out.println("no, i am dead.");
           }
   
           // 下面的代码和上面的代码一样。但是对象却自救失败了。
           // 对象第二次进行成功的拯救
           SAVE_HOOK = null;
           System.gc();
           // 因为finalizer方法的优先级很低，所以暂停0.5s,以等待他运行
           Thread.sleep(600);
           if (SAVE_HOOK != null) {
               SAVE_HOOK.isAlive();
           } else {
               System.out.println("no, i am dead.");
           }
       }
   ```






























