class MyStack {
    // using linkedlist as queue
    LinkedList<Integer> myStack;
    public MyStack(){
        myStack = new LinkedList<Integer>();
    }
    // Push element x onto stack.
    public void push(int x) {
        //store as a list
        myStack.add(x);
    }

    // Removes the element on top of the stack.
    public void pop() {
        LinkedList<Integer> list = new LinkedList<Integer>();
        // push the data of myStack into list except myStack[0]
        while(myStack.size()>1){
            list.add(myStack.poll());
        }
        myStack = list;
    }

    // Get the top element.
    public int top() {
        LinkedList<Integer> list = myStack;
        ArrayList<Integer> arr = new ArrayList<Integer>(); // ������¼��������ֵ
        while(list.size()>0){
            arr.add(list.poll());
        }
        // �ָ�myStack
        for (int i = 0; i < arr.size(); i++) {
			myStack.add(arr.get(i));
		}
        return arr.get(arr.size()-1);
        /*����list��myStackָ��ĵ�ַһ��������myStackҲ��������
        LinkedList<Integer> list = myStack;
        if(list==null || myStack.size()==0){
            return -1;
        }
        while(list.size()>1){
            list.pop();
        }
        return list.peek().intValue();*/
    }

    // Return whether the stack is empty.
    public boolean empty() {
        if(myStack.size()==0){
            return true;
        }
        return false;
    }
}