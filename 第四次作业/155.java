class MinStack {
    Stack<Integer> stack = new Stack<Integer>();
    Stack<Integer> minStack = new Stack<Integer>();
//    LinkedList<Integer> list = new LinkedList<Integer>();
    public void push(int x) {
        stack.push(x);
        if(minStack.empty()){
            minStack.push(x);
        }else{
            if(x<=minStack.peek()){
                minStack.push(x);
            }
        }
        /*if(list.size()==0){
            list.add(x);
            return;
        }
        int right = list.size()-1;
        int left = 0;
        int mid = (right+left)/2;
        while(left<right){
            if(x<=list.get(mid)){
                right = mid-1;
            }else{
                left=mid+1;
            }
            mid=(left+right)/2;
        }
        list.add(mid+1,x);*/
    }

    public void pop() {
        int num=stack.pop();
        if(num==minStack.peek()){
            minStack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
        /*
        ArrayList<Integer> list = new ArrayList<Integer>();
        Stack<Integer> newStack = new Stack<Integer>();
        while(!stack.empty()){
            list.add(stack.pop());
        }
        for(int i=list.size()-1; i>=0; i--){
            stack.push(list.get(i));
        }
        int min = 0;
        for(int i=0; i<list.size();i++){
            if(list.get(i)<min){
                min=list.get(i);
            }
        }
        return min;
        */
    }
}
