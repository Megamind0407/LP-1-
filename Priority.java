
import java.util.Scanner;
 
public class Priority {
        
    public static void main(String args[]) {
            try (Scanner s = new Scanner(System.in)) {
              int ct[],a[],x,n,p[],pp[],bt[],w[],t[],i,k=0;
	          float atat,awt;
              p = new int[10];
              pp = new int[10];
              bt = new int[10];
              w = new int[10];
              t = new int[10];
   a= new int[10];
  ct=new int[10];
  //n is number of process
  //p is process
  //pp is process priority
  //bt is process burst time
  //w is wait time
  // t is turnaround time
  //awt is average waiting time
  //atat is average turnaround time
 
 
  System.out.print("Enter the number of process : ");
  n = s.nextInt();
  System.out.print("\n\t Enter burst time : time priorities  : arrival time\n");
 
  for(i=0;i<n;i++)
  {
    System.out.print("\nProcess["+(i+1)+"]:");
   bt[i] = s.nextInt();
   pp[i] = s.nextInt();
   a[i]=s.nextInt();
   p[i]=i+1;
  }
 //SORT ON THE BASIS OF ARRIVAL TIME AND PRIORITY
 for(i=0;i<n-1;i++)
  {
  
   for(int j=i+1;j<n;j++)
   {

    if(a[i]>=a[j] || pp[i]>pp[j])
    {
   x=pp[i];
   pp[i]=pp[j];
   pp[j]=x;
   x=bt[i];
   bt[i]=bt[j];
   bt[j]=x;
   x=p[i];
   p[i]=p[j];
   p[j]=x;
    x=a[i];
   a[i]=a[j];
   a[j]=x;
  }
   
  }

}
//sorting on the basis of priority

 for(i=1;i<=n;i++)
  {
  if(i==1)
   {  k=bt[0];

ct[0]=k;}
  else{
    k=bt[i-1]+k;
ct[i-1]=k;}
   for(int j=i+1;j<=n;j++)
   {

    if(pp[i]<pp[j] && a[j]<=k)
    {
   x=pp[i];
   pp[i]=pp[j];
   pp[j]=x;
   x=bt[i];
   bt[i]=bt[j];
   bt[j]=x;
   x=p[i];
   p[i]=p[j];
   p[j]=x;
   x=a[i];
   a[i]=a[j];
   a[j]=x;
  }
  }
}
w[0]=0;
awt=0;
t[0]=bt[0];
atat=t[0];
for(i=1;i<n;i++)
 {
 
  t[i]=ct[i]-a[i];
  w[i]=t[i]-bt[i];
  awt+=w[i];
  atat+=t[i];
 }
 
//prority logic

//Displaying the process
 
 System.out.print("\n\nProcess \t Arrival Time \t Burst Time \t Wait Time \t Turn Around Time   \tPriority \n");
for(i=0;i<n;i++)
 System.out.print("\n   "+p[i]+"\t\t\t   "+a[i]+"\t\t\t   "+bt[i]+"\t\t\t     "+w[i]+"\t\t\t     "+t[i]+"\t\t\t     "+pp[i]+"\n");
awt/=n;
atat/=n;
 System.out.print("\n Average Wait Time : "+awt);
System.out.print("\n Average Turn Around Time : "+atat);
            }
 
        }
}
