//顺序表
class MyArrayList<E>{
    private Object[] element;
    private int capacity;
    private int size;

    public MyArrayList(){
        element=new Object[10];
        capacity=10;
    }

    public MyArrayList(int initcapacity){
        if(initcapacity<0||initcapacity>Integer.MAX_VALUE){
            throw new IllegalArgumentException("容量不合法");
        }else{
            element=new Object[initcapacity];
            this.capacity=initcapacity;
        }
    }

    //尾插
    public boolean add(E e){
        resize(size+1);
        element[size++]=e;
        return true;
    }
    //在指定位置添加元素
    public boolean add(int index,E e){
        if(index<0||index>=capacity){
            return false;
        }
        resize(size+1);
        System.arraycopy(element,index,element,index+1,size-index);
        element[index]=e;
        size++;
        return true;
    }
    private void resize(int minCapacity){
        if(minCapacity<=element.length) return;
        int oldCapacity=element.length;
        int newCapacity=oldCapacity+(oldCapacity>>1);
        if(newCapacity<minCapacity) newCapacity=minCapacity;
        element=Arrays.copyOf(element,newCapacity);
        this.capacity=newCapacity;
    }

    //删除指定位置元素
    public E remove(int index){
        if(index<0||index>=size) return null;
        E ret=(E)element[index];
        System.arraycopy(element,index+1,element,index,size-index-1);
        size--;
        return ret;
    }

    //修改元素
    public void set(int index,E e){
        if(index<0||index>=size) return;
        element[index]=e;
    }

    //获取某个位置的元素
    public E get(int index){
        if(index<0||index>=size) return null;
        return (E)element[index];
    }

    //返回元素个数
    public int size(){
        return size;
    }

    //判断是否为空
    public boolean isEmpty(){
        return 0==size;
    }
}


class MyPriority<E>{
    private Object[] arr;
    private int size;
    private Comparator<? super E> comparator;
    public MyPriority(){
        arr=new Object[10];
    }
    public MyPriority(int capacity,Comparator<? super E> comparator){
        arr=new Object[capacity];
        this.comparator=comparator;
    }
    public MyPriority(int capacity){
        this(capacity,null);
    }
    public MyPriority(Comparator<? super E> comparator){
        this(10,comparator);
    }

    //入队
    public void offer(E e){
        grow(size+1);
        arr[size++]=e;
        shiftUp(size-1);
    }

    //向上调整
    private void shiftUp(int child) {
        int parent=(child-1)/2;
        while(child!=0){
            if(comparator.compare((E)arr[child],(E)arr[parent])<0){
                swap(child,parent);
                child=parent;
                parent=(child-1)/2;
            }else{
                break;
            }
        }
    }

    //出队
    public E poll(){
        if(size<0) return null;
        E ret=(E)arr[0];
        swap(0,size-1);
        size--;
        shiftDown(0);
        return ret;
    }

    //向下调整
    private void shiftDown(int parent) {
        int left=2*parent+1;
        while(left<size){
            if(left+1<size&&comparator.compare((E)arr[left],(E)arr[left+1])>0){
                left+=1;
            }
            if(comparator.compare((E)arr[left],(E)arr[parent])<0){
                swap(left,parent);
                parent=left;
                left=2*parent+1;
            }else{
                break;
            }
        }
    }

    public E peek(){
        if(0==size) return null;
        return (E)arr[0];
    }

    public int size(){
        return size;
    }
    //扩容
    private void grow(int i) {
        if(i>arr.length){
            arr=Arrays.copyOf(arr,arr.length*2);
        }
    }
    private void swap(int i,int j){
        E tmp=(E)arr[i];
        arr[i]=arr[j];
        arr[j]=tmp;
    }
}




