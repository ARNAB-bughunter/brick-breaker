import java.awt.*;
import  java.awt.event.*;
import javax.swing.*;
import java.util.*;
class test {
  static JFrame frame;
  static Container c;
  static JProgressBar jb;
  static int bar=0;
  static JLabel loading;
public static void main(String[] args) {
frame=new JFrame("");
JLabel label=new JLabel();
frame.setVisible(true);
frame.setResizable(false);
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.setBounds(10,10,810,855);
c=frame.getContentPane();
c.setLayout(null);
c.setBackground(Color.yellow);
jb=new JProgressBar(0,100);
loading=new JLabel("Loading....");

jb.setBounds(150,350,500,40);
jb.setValue(0);
jb.setStringPainted(true);
jb.setForeground(Color.red);
jb.setFont(new Font("Comic Sans MS",Font.BOLD,35));
loading.setFont(new Font("Comic Sans MS",Font.BOLD,35));
loading.setBounds(320,400,300,50);
c.add(jb);
c.add(loading);
while(bar<=100){
        try{Thread.sleep(500);}catch(Exception e){}
        jb.setValue(bar);
        bar=bar+10;
            }          
loading.setVisible(false);              
jb.setVisible(false);            
make objy=new make();
frame.addKeyListener(objy);
c.add(objy);
back_color objx=new back_color();
objx.run();
  }
static class back_color implements Runnable{
public void run()
{
while(true){
try{Thread.sleep(1000/2);}catch(Exception e){}
c.setBackground(Color.yellow);  
try{Thread.sleep(1000/2);}catch(Exception e){}
c.setBackground(Color.magenta);
try{Thread.sleep(1000/2);}catch(Exception e){}
c.setBackground(Color.orange);
try{Thread.sleep(1000/2);}catch(Exception e){}
c.setBackground(Color.blue);
  }
}
  }

}
class make extends JPanel implements KeyListener{
Rectangle rectpad;
Rectangle rectball;
boolean status;
boolean connection=true;
int x=350,y=720,dx=1,dy=3,padx=320; 
int point=0;
int row=10;
int arr2[][]=new int[row][5];
int total_brick=row*5;
int conx=350;
int life=5;
JLabel label=new JLabel("",JLabel.CENTER);
JLabel label1=new JLabel("",JLabel.CENTER);
JLabel name=new JLabel("ARNAB",JLabel.CENTER);
public void paintComponent(Graphics g){ 
  super.paintComponent(g);
  g.setColor(Color.cyan);
  g.setFont(new Font("Arial",Font.BOLD,30));
  g.drawString("Score="+String.valueOf(point),0,780);
  g.drawString("Life="+String.valueOf(life),670,780);
  g.setColor(Color.green);
  if(connection==false)
     g.fillOval(x,y,20,20);
  else
     g.fillOval(conx,725,20,20);
  g.setColor(Color.red);
  g.fillRect(padx,745,80,11);
  g.setColor(Color.yellow);
  g.drawLine(0,756,761,756);  
  brick(g);
                
  }     
make(){ 
initialize();  
label.setBounds(185,450,350,50);
label1.setBounds(185,500,400,50);
name.setBounds(305,765,100,20);
label.setFont(new Font("Comic Sans MS",Font.BOLD,30));
label1.setFont(new Font("Comic Sans MS",Font.BOLD,30));
name.setFont(new Font("Comic Sans MS",Font.BOLD,15));
label.setForeground(Color.cyan);
label1.setForeground(Color.cyan);
name.setForeground(Color.cyan);
label.setText("Pess Enter To Start");
this.add(label); 
this.add(label1); 
this.add(name); 
this.setLayout(null);     
this.setBounds(15,15,761,790);
this.setBackground(Color.black);
  }
class move extends Thread{
public void run(){   
status=true; 
while(status){  
x=x+dx;
y=y+dy;
if(x<=0 || x>=748)
dx*=-1;
if(y<=0)
dy*=-1;
rectball=new Rectangle(x,y,20,20);
rectpad=new Rectangle(padx-3,740,83,11);
if(rectball.intersects(rectpad)){
  dy*=-1;
}
if(y>741){
  status=false; 
x=padx+20;
y=720;
label.setForeground(Color.cyan);
label1.setText("Press Enter To Continue");
if(life<=0){
label.setText("Game Over");
label1.setText("Press Enter To Restart");
total_brick=row*5;
initialize();
point=0;
}
connection=true;
life=life-1;
}
if(life<=0){
  status=false;
label.setForeground(Color.cyan);
label.setText("Game Over");
label1.setText("Press Enter To Restart");
connection=true;
}
int s=115,t=100;
A:for(int i=0;i<row;i++){
  for(int j=0;j<5;j++){
  Rectangle  rectx=new Rectangle(s,t,100,11); 
  if(rectx.intersects(rectball) && arr2[i][j]==1){
    if(x+19<=s || x+1>=s+100)
       dx*=-1;
    else
        dy*=-1;     
  total_brick=total_brick-1;
  point=point+5;
  arr2[i][j]=0;
  break A;
  }
      s+=100;
         }
       s=115;
        t=t+30;
      }      
repaint();
if(total_brick==0){
status=false;
label.setText("YOU WIN!!!!!");
label1.setText("Press Enter To Start");
connection=true;
}
try{Thread.sleep(5);}catch(Exception e){} 
    }    
   }
}
public void keyReleased(KeyEvent key){}
public void keyTyped(KeyEvent key){}
public void keyPressed(KeyEvent key){
if(key.getKeyCode()==KeyEvent.VK_LEFT){
label.setText("");  
label1.setText("");  
padx=padx-80;
conx=padx+25;
repaint();
}
if(key.getKeyCode()==KeyEvent.VK_RIGHT){
label.setText("");  
label1.setText("");
padx=padx+80;
conx=padx+25;
repaint();
}
if(key.getKeyCode()==KeyEvent.VK_ENTER){
  move obj=new move();obj.start();
  connection=false;
  label.setText(""); 
  label1.setText(""); 
  x=padx+40;
  y=720;
  status=true;
  if(life<=0){
     initialize();
  total_brick=row*5;
    life=5;
    point=0;
  }
 if(total_brick==0){
  initialize();
    total_brick=row*5;
    life=5;
    point=0;
  } 
 }
if(padx<0){padx=0;conx=25;}
if(padx>678){padx=678;conx=padx+25;}

repaint();
   }

public void brick(Graphics g){
  int s=115,t=100;
for(int i=0;i<row;i++){
  for(int j=0;j<5;j++){
    g.setColor(Color.white);
    if(arr2[i][j]==1)
    g.fillRect(s,t,100,30);
    g.setColor(Color.blue);
    if(arr2[i][j]==1)
    g.drawRect(s,t,100,30);
    s+=100;
    }
    s=115;
    t=t+30;
   }
 }
public void initialize(){
for(int i=0;i<row;i++)
  for(int j=0;j<5;j++)
    arr2[i][j]=1;
 }    
}  