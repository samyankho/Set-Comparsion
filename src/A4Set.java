import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class A4Set<E extends Comparable<E>> implements Set<E>{

	public static class AVLNode<E> {

	    public AVLNode<E> left;//left node

	    public AVLNode<E> right;//right node

	    public E data;
	    
	    public int height;//height of node

	    public AVLNode(E data) {
	        this(null,null,data);
	    }

	    public AVLNode(AVLNode<E> left, AVLNode<E> right, E data) {
	        this(left,right,data,0);
	    }

	    public AVLNode(AVLNode<E> left, AVLNode<E> right, E data, int height) {
	        this.left=left;
	        this.right=right;
	        this.data=data;
	        this.height = height;
	    }
	}
	
	
	
	private AVLNode<E> root;
	private int size;
	
	public A4Set(){
		root = null;
		size = 0;
	}
	
	@Override
	public boolean add(E e) {
		if(e == null) {
			return false;
		}else {
			this.root = insert(e, root);
			return true;
		}
	}
	
	private int height(AVLNode<E> t )
    {
        return t == null ? -1 : t.height;
    }
	
	
	private AVLNode<E> insert(E element , AVLNode<E> p){

		   //create a new node
		   if(p==null){
		       p=new AVLNode<E>(element);
		   }
		   int result = element.compareTo(p.data);
		   if(result < 0){
			   //element less than node then find position at left
		       p.left=insert(element,p.left);

		       //recalculate height of the tree, if =2 then need to re balance, left > right
		       if(height(p.left)-height(p.right)==2){
		           //decide data is the left child or right child of the node
		           if(element.compareTo(p.left.data)<0){
		               //do a LL rotation
		               p=singleRotateLeft(p);
		           }else {
		               //do a LR rotation
		               p=doubleRotateWithLeft(p);
		           }
		       }
		   }else if (result > 0){
			   //element greater than node then find position at left
		       p.right=insert(element,p.right);

		       if(height(p.right)-height(p.left)==2){
		           if (element.compareTo(p.right.data)<0){
		               //do a RL rotation
		               p=doubleRotateWithRight(p);
		           }else {
		               p=singleRotateRight(p);
		           }
		       }
		   }
		   else
		    ;//if element exist do nothing
		   //recalculate the height of the tree
		   p.height = Math.max( height( p.left ), height( p.right ) ) + 1;

		   return p;
		}
	
	
	private AVLNode<E> singleRotateLeft(AVLNode<E> x){
	    //turn w as root node
	    AVLNode<E> w=  x.left;
	    //at the mean time right child of w turn into left child of x
	    x.left=w.right;
	    //turn x into w's right child
	    w.right=x;
	    //recalculate height of x/w
	    x.height=Math.max(height(x.left),height(x.right))+1;
	    w.height=Math.max(height(w.left),x.height)+1;
	    return w;//return the new root node
	}
	

	private AVLNode<E> singleRotateRight(AVLNode<E> w){

	    AVLNode<E> x=w.right;

	    w.right=x.left;
	    x.left=w;

	    //recalculate height of x/w
	    w.height=Math.max(height(w.left),height(w.right))+1;
	    x.height=Math.max(height(x.left),w.height)+1;

	    //return the new root node
	    return x;
	}
	
	
	private AVLNode<E> doubleRotateWithLeft(AVLNode<E> x){
	    //do a w RR rotation
	    x.left=singleRotateRight(x.left);
	    //then do a LL rotation on x
	    return singleRotateLeft(x);
	}
	
	
	private AVLNode<E> doubleRotateWithRight(AVLNode<E> x){
	    //LL rotation
	    x.right=singleRotateLeft(x.right);
	    //then rotation
	    return singleRotateRight(x);
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean contains(Object o) {
		if(o == null) {
			return false;
		}else {
		return contain((E)o, root);
	}
	}
	
	private boolean contain( E x, AVLNode<E> t ){
        while( t != null )
        {
            int compareResult = x.compareTo( (E) t.data );
            
            if( compareResult < 0 )
                t = t.left;
            else if( compareResult > 0 )
                t = t.right;
            else
                return true;    // Match
        }
        return false;   // No match
    }
	
	
	@Override
	public int size() {
		return countnodes(root);
	}
	
	private int countnodes(AVLNode<E> r) {
		if(r == null) {
			return 0;
		}else {
			size = 1;
			size += countnodes(r.left);
			size += countnodes(r.right);
			return size;
		}
	}
	
	
	@Override
	public void clear() {
		this.root = null;
		this.size = 0;
	}
	
	
	@Override
	public String toString() {
		return printTree(root);
	}
	
	private String printTree(AVLNode<E> tree) {
        if (tree == null) {
            return null;
        }else {
        	String a = "";
        	if(tree.left != null) {
        		a += printTree(tree.left);
        	}
        	a += a.toString() + " "; 
        	if(tree.right != null) {
        		a += printTree(tree.right);
        	}
        	return a;
        }
    }


	@SuppressWarnings("unchecked")
	@Override
	public boolean remove(Object o) {
		if(o == null || root == null) {
			return false;
		}else if(contains(o) == false){
			return false;
		}else {
			root = delete((E) o, root);
			return true;
		}
	}
	
	private AVLNode<E> delete(E data,AVLNode<E> p){

	    int result=data.compareTo(p.data);

	    //find the element on left child
	    if(result<0){
	        p.left=delete(data,p.left);

	        //check if balance or not
	        if(height(p.right)-height(p.left)==2){
	            AVLNode<E> currentNode=p.right;
	            //decide which rotation
	            if(height(currentNode.left)>height(currentNode.right)){
	                //LL
	                p=singleRotateLeft(p);
	            }else{
	                //LR
	                p=doubleRotateWithLeft(p);
	            }
	        }

	    }
	    //find element on right child
	    else if(result>0){
	        p.right=delete(data,p.right);
	        //check if balance or not
	        if(height(p.left)-height(p.right)==2){
	            AVLNode<E> currentNode=p.left;
	            //decide which rotation
	            if(height(currentNode.right)>height(currentNode.left)){
	                //RR
	                p=singleRotateRight(p);
	            }else{
	                //RL
	                p=doubleRotateWithRight(p);
	            }
	        }
	    }
	    //found the element that need to delete
	    //case of node have two child
	    else if(p.right!=null && p.left!=null){

	        //find the replace node
	        p.data=findMin(p.right).data;

	        //delete the node that need to replace
	        p.right = delete( p.data, p.right );
	    }
	    else {
	        //case of node only one child or no child
	        p=(p.left!=null)? p.left:p.right;
	    }

	    //recalculate the height
	    if(p!=null)
	        p.height = Math.max( height( p.left ), height( p.right ) ) + 1;
	    return p;
	}

	private AVLNode<E> findMin(AVLNode<E> r)
    {
        if( r == null ) {
            return null;
        }
        while( r.left != null )
            r = r.left;
        return r;
    }


	@Override
	public boolean isEmpty() {
		return this.root == null;
	}



	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Iterator iterator() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object[] toArray() {
		throw new UnsupportedOperationException();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object[] toArray(Object[] a) {
		throw new UnsupportedOperationException();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean containsAll(Collection c) {
		throw new UnsupportedOperationException();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean addAll(Collection c) {
		throw new UnsupportedOperationException();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean retainAll(Collection c) {
		throw new UnsupportedOperationException();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean removeAll(Collection c) {
		throw new UnsupportedOperationException();
	}


}
