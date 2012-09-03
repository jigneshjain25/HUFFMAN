import java.util.*;
class node{
        public char status;
        public String code;
        public char key;
        public int freq;
        public node left,right,parent;
        public node(){

        }
        public node(char k,int f){
                key=k;
                freq=f;
        }
        public void solve(){
                if(parent!=null){
                        code=parent.code+Character.toString(status);
                }
                if(left!=null)
                left.solve();
                if(right!=null)
                right.solve();
                if(key!='*')
                System.out.println(key+" "+code);

        }
}
class nodeComparator implements Comparator<node>
{
    
    public int compare(node x, node y)
    {
        if(x.freq>y.freq)return 1;
        return 0;
        
    }
}

public class Huffman_Encoder{
        public static void main(String[] args){
                int freq;
                char key;
                Comparator<node> comparator = new nodeComparator();
                Scanner cin=new Scanner(System.in);
                System.out.println("Enter a String");
                String s=cin.nextLine();
                HashMap<Character,Integer> hm = new HashMap<Character,Integer>();
                for(int i=0;i<s.length();i++){
                         if(!hm.containsKey(s.charAt(i)))
                                hm.put(s.charAt(i),1);
                         else
                         hm.put(s.charAt(i),hm.get(s.charAt(i))+1);
                }
                Set set = hm.entrySet();
                Iterator i = set.iterator(); 
                PriorityQueue<node> q=new PriorityQueue<node>(1000,comparator);
                while(i.hasNext()) { 
                         Map.Entry me = (Map.Entry)i.next(); 
                         key=(char)me.getKey(); 
                         freq=(int)me.getValue();
                         q.add(new node(key,freq));
                } 
                while(q.size() > 1)
                {
       			node r=new node();
                        node n=q.remove();
                        n.status='0';  //denotes left child
                        n.parent=r;
                        node m=q.remove();
                        m.status='1';  //denotes right child
                        m.parent=r;
                        r.key='*';
                        r.freq=n.freq+m.freq;
                        r.left=n;
                        r.right=m;
                        q.add(r);
		}

                 node t=q.remove();
                 t.status='$'; //denotes it is the root
                 t.code="";
                 t.parent=null;
                 t.solve();
	}
}
