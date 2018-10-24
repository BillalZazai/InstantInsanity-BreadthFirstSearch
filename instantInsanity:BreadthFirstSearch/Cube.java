public class Cube {
	private Color [] faces;
	private  Color [] temp;
	private int count=0;
	Cube (Color [] faces ){
		this.faces=faces;
		temp=new Color [faces.length];
		for (int i=0; i<faces.length;i++){
			temp[i]=faces[i];
		}
	}
	Cube (Cube other ){
		faces = new Color []{other.getUp(), other.getFront(),other.getRight(),other.getBack(),other.getLeft(),other.getDown()};	
		temp=new Color [faces.length];
		for (int i=0; i<faces.length;i++){
			temp[i]=faces[i];
		}
	}
	
	public Color getUp(){ return faces[0]; }
	
	public Color getFront(){ return faces[1]; }
	
	public Color getRight(){ return faces[2]; }
	
	public Color getBack(){ return faces[3]; }
	
	public Color getLeft(){ return faces[4]; }
	
	public Color getDown(){ return faces[5]; }

	public String toString () { 
		return ("["+faces[0]+", "+faces[1]+", "+faces[2]+", "+faces[3]+", "+faces[4]+", "+faces[5]+ "]");
	}
	public boolean hasNext (){
		return (count<24);
	}

	private void Rotate (){
		Color front=faces[faces.length-2];
		for (int i=faces.length-3; i>0;i--){
			faces[i+1]=faces[i];
		}
		faces[1]=front;
		count++;
	}
	private void RightRoll (){
		Color [] a= new Color [faces.length];
		for (int i=0; i<faces.length; i++){
			a[i]=faces[i];
		}
		faces[0]=a[4];
		faces[2]=a[0];
		faces[5]=a[2];
		faces[4]=a[5];
		count++;
	}
	private void LeftRoll (){
		Color [] a= new Color [faces.length];
		for (int i=0; i<faces.length; i++){
			a[i]=faces[i];
		}
		faces[0]=a[2];
		faces[2]=a[5];
		faces[4]=a[0];      //System.arraycopy ()
		faces[5]=a[4];
		count++;
	}
	private void Identity (){
		for (int i=0; i<temp.length;i++){
			faces[i]=temp[i];
		}
		count++;
	}
	public void reset (){
		Identity();
	}
	public void next ()throws IllegalStateException{
		if (count==0){ 
			Identity ();
		}
		else if (count==4||count==8||count ==20){
			RightRoll ();
		}
		else if (count==12||count==16){
			LeftRoll ();
		}
		else if ((count>0 && count<4)||(count>4&&count<8)||(count>8&&count<12)||(count>12&&count<16)||(count>16&&count<20)||(count>20&&count<24)){
			Rotate ();
		}
		else if (count>23){
			throw new IllegalStateException () ;
			//System.out.println ("All orientations have been Done");
		}
	}
	public Cube copy (){
		Cube c=new Cube (new Color [] {getUp (),getFront(),getRight(),getBack(),getLeft(),getDown()});
		return c;
	}

















}