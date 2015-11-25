import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;
/**
 * class SortedList, an interface for List that is always sorted
 * @author Jason Ginsberg 
 * @version October 2014
 */
public class SortedList implements List<Comparable> 
{   
    private static final int DEFAULT_CAPACITY = 3; 
    private Comparable [] data;
    private int capacity;
    private int numItems;

    public SortedList(){
        data = new Comparable[DEFAULT_CAPACITY];
        capacity = DEFAULT_CAPACITY;
        numItems = 0;
    }

    public SortedList(int capacity){
        data = new Comparable[capacity];
        this.capacity = capacity;
        numItems = 0;
    }

    public SortedList(Collection<? extends Comparable> other)
    {
        throw new UnsupportedOperationException("not supported by this list type");
    }

    public boolean add(Comparable item){
        if (!(item instanceof Comparable)){ // this will never be called
            throw new ClassCastException(item + " is not a Comparable");
        } 
        if (item==null){
            throw new NullPointerException();
        }
        if (numItems>=capacity){
            capacity= 2*capacity;
            Comparable [] resize = new Comparable[capacity];              
            for (int i = 0;i<numItems;i++){
                resize[i] = data[i];
            }
            data = resize;
        }
        for (int i = 0; i<capacity;i++){
            if (i==numItems||data[i].compareTo(item)>=0){ 
                int j = numItems;
                while (j>i){
                    data[j] = data[j - 1];
                    --j;
                }
                data[i] = item;
                ++numItems;
                return true;
            }
        }    
        return false;
    }

    public void add(int index, Comparable item){
        throw new UnsupportedOperationException(
            "Addition at a specific location is not supported");
    }

    public boolean addAll(int index, Collection<? extends Comparable>items){
        throw new UnsupportedOperationException(
            "Adding item at a specific location is not supported");
    }

    public boolean addAll(Collection<? extends Comparable>items){
        throw new UnsupportedOperationException(
            "Adding item at a specific location is not supported");
    }

    public void clear(){
        for (int i = 0; i<capacity;i++){
            data[i] = null;
        }
        numItems = 0;
    }

    public boolean contains(Object o){
        if (!(o instanceof Comparable)){ 
            throw new ClassCastException(o + " is not a Comparable");
        } 
        //more efficient than iterating through entire list
        int bottom = 0;
        int top = numItems+1;
        while (bottom!=top&&((bottom+top)/2)<numItems){
            int middle = (bottom+top)/2;
            if (((Comparable)o).compareTo(data[middle])<0){
                top=middle;
            }
            else if (((Comparable)o).compareTo(data[middle])>0){
                bottom=middle;
                if (top-bottom==1){
                    return false;
                }
            }
            else{
                return true;
            }
        }
        return false;
    }

    public boolean containsAll(Collection<?> c){
        throw new UnsupportedOperationException("not supported by this list type");
    }

    public boolean equals(Object o) 
    { 
        if (!(o instanceof List)) 
        {
            throw new ClassCastException (o + " is not a List");
        }
        List otherList = (List)o; 
        if (otherList.size()==size()){
            for (int i = 0; i<=numItems;i++){
                Comparable item = (Comparable)(otherList.get(i));
                if (item.compareTo(data[i])!=0){
                    return false;
                }
            }
            return true;  
        }
        return false;
    }

    public Comparable get(int index){
        if (index<0|| index>=size()){
            throw new IndexOutOfBoundsException("index out of range");
        }
        return data[index];
    }

    public int hashCode(){
        int hashCode = 1;
        for (Comparable item : data){
            hashCode = 31*hashCode + (item==null ? 0 : item.hashCode());
        }
        return hashCode;
    }

    public int indexOf(Object o){
        if (o == null){
            throw new NullPointerException();
        }
        for (int i = 0; i<numItems;i++){
            if (((Comparable)o).compareTo(data[i])==0){
                return i;
            }
        }
        return -1;
    }

    public int lastIndexOf(Object o){
        if (o == null){
            throw new NullPointerException();
        }
        //more efficient than a complete iteration
        int bottom = 0;
        int top = numItems+1;
        while (bottom!=top&&((bottom+top)/2)<numItems){
            int middle = (bottom+top)/2;
            if (((Comparable)o).compareTo(data[middle])<0){
                top=middle;
            }
            else if (((Comparable)o).compareTo(data[middle])>0){
                bottom=middle;
                if (top-bottom==1){
                    return -1;
                }
            }
            else{
                return middle;
            }
        }
        return -1;
    }

    public boolean isEmpty(){
        return numItems==0;
    }

    public Iterator<Comparable> iterator(Comparable item){
        throw new UnsupportedOperationException("not supported by this list type");
    }

    public Iterator<Comparable> iterator(){
        throw new UnsupportedOperationException("not supported by this list type");
    }

    public ListIterator<Comparable> listIterator(int index){
        throw new UnsupportedOperationException("not supported by this list type");
    }

    public ListIterator<Comparable> listIterator(){
        throw new UnsupportedOperationException("not supported by this list type");
    }

    public Comparable remove(int index){
        if (index<0|| index>=size()){
            throw new IndexOutOfBoundsException("index out of range");
        }
        Comparable removed = data[index];
        if (removed!=null){
            int i = index;
            while (i<=numItems-1){
                if (i == numItems-1){
                    data[i] = null;
                }
                else{
                    data[i]=data[i+1];
                }
                ++i;
            }
            numItems-=1;
        }
        return removed;
    }

    public boolean remove(Object o){
        for (int i = 0; i<numItems;i++){
            if (((Comparable)o).compareTo(data[i])==0){
                while (i<=numItems-1){
                    if (i == numItems-1){
                        data[i] = null;
                    }
                    else{
                        data[i]=data[i+1];
                    }
                    ++i;
                }
                numItems-=1;
                return true;
            }
        }
        return false;
    }

    public boolean removeAll(Collection<?> c){
        throw new UnsupportedOperationException("not supported by this list type");
    }

    public boolean retainAll(Collection<?> all){
        throw new UnsupportedOperationException("not supported by this list type");
    }

    public Comparable set (int index, Comparable item){
        if (item == null){
            throw new NullPointerException();
        }
        if (!(item instanceof Comparable)){
            throw new ClassCastException(item+"is not a comparable");
        }
        if (index<0|| index>=size()){
            throw new IndexOutOfBoundsException("index out of range");
        }
        if (data[index].compareTo(data[index+1])<0||
        data[index].compareTo(data[index-1])>0){
            return null;
        }
        Comparable old = data[index];
        data[index]  = item;
        return old;
    }

    public int size(){
        return numItems;
    }

    public List<Comparable> subList (int from, int to){
        if (to>numItems+1){
            throw new IndexOutOfBoundsException("index out of range");  
        }
        int capacity = to-from;
        List<Comparable> subList = new ArrayList(capacity); 
        for (int i = from; i<to;i++){
            subList.add(data[i]);
        }
        return subList;
    }

    public Object [] toArray(){
        Comparable [] copy = new Comparable[numItems];  
        if (data == null){
            throw new NullPointerException();   
        }
        for (int i = 0; i<numItems;i++){
            copy[i] = data[i];
        }
        return copy;
    }

    public <T> T[] toArray(T[] a){
        throw new UnsupportedOperationException("not supported by this list type");
    }

    @Override
    public String toString(){
        String result = "[";
        for (int i = 0; i<numItems;i++){
            Comparable item = data[i];
            result+=item;
            if (i != numItems-1){
                result+= ", ";
            }
        }
        result += "]";
        return result;
    }
}