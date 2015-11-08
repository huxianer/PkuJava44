class MyQueue {
    Stack<Integer> myQueue = new Stack<>();
    // Push element x to the back of queue.
    public void push(int x) {
        myQueue.push(x);
    }

    // Removes the element from in front of queue.
    public void pop() {
        ArrayList<Integer> arr = new ArrayList<Integer>();
        while(myQueue.size()>0){
            arr.add(myQueue.pop());
        }
        for(int i=arr.size()-2; i>=0; i--){
            myQueue.push(arr.get(i));
        }
    }

    // Get the front element.
    public int peek() {
        ArrayList<Integer> arr = new ArrayList<Integer>();
        while(myQueue.size()>0){
            arr.add(myQueue.pop());
        }
        for(int i=arr.size()-1; i>=0; i--){
            myQueue.push(arr.get(i));
        }
        return arr.get(arr.size()-1);
    }

    // Return whether the queue is empty.
    public boolean empty() {
        if(myQueue.empty()){
            return true;
        }
        return false;
    }
}