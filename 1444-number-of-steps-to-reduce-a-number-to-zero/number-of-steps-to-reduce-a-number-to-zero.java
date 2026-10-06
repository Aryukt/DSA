class Solution {
    public int numberOfSteps(int num) {
        int steps = 0;
       while(num!=0){
        if(num%2==0){
            int ans =num/2;
            num=ans;
        
        }
        else if(num%2!=0){
            int anss = num-1;
            num=anss;
        }
        steps++;
       }
       return steps;
    }
    }