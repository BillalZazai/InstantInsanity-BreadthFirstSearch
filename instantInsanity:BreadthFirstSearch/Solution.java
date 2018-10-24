public class Solution {
	private Cube [] cubes ;
	private int size;
	private static int numberOfIsValidCalls=0;
	private int nextCount =0;
	Solution (Cube []cubes) {
		this.cubes=cubes;
		size=cubes.length;
	}
	Solution (Solution other, Cube c){
		try{	
			size = other.size()+1;
			cubes=new Cube [size];
			for (int i =0 ;i<other.size();i++){
				cubes[i]=new Cube (other.getCube(i));
			}
			cubes[size-1]=new Cube (c);
		}
		catch (NullPointerException e){
			System.out.print ("your Solution is null");
		}
	}
	public int size (){
		return size;
	}
	public static void resetNumberOfCalls () {
		numberOfIsValidCalls=0;
	}
	public static  int getNumberOfCalls (){
		return numberOfIsValidCalls;
	}
	public Cube getCube (int pos)throws IndexOutOfBoundsException{
		if (pos<0){
			throw new IndexOutOfBoundsException("out of bound");
		}
		Cube c;
		c=cubes[pos];
		return c;
	}
	public boolean isValid (){
		numberOfIsValidCalls++;
		boolean isValid=true;
		for (int i=0; i<size;i++){
			for (int j=i+1; j<size;j++){
				if (cubes[j].getFront()==cubes[i].getFront()||cubes[j].getBack()==cubes[i].getBack()||cubes[j].getRight()==cubes[i].getRight()||cubes[j].getLeft()==cubes[i].getLeft()){
					isValid=false;
				}
			}
		}
		return isValid;
	}
	public boolean isValid (Cube next){
		Solution s = new Solution (this, next);
		return s.isValid ();
	}

	public String toString (){
		String string="";
		for (int i =size-1; i>=0;i--){
			string=string+"[";
			string= string+cubes[i].getUp ()+",";
			string= string+cubes[i].getFront()+",";
			string=string+cubes[i].getRight()+",";
			string=string+cubes[i].getBack()+",";
			string=string+cubes[i].getLeft()+",";
			string=string+cubes[i].getDown ();
			string=string+"]";
			string=string+"\n";
		}
		return string;
	}
}