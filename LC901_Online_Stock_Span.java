// A Stack and a HashMap, the stack is used to store the price, hashmap is used to record the already removed number of a price
class StockSpanner {
    
    Stack<Integer> stack;
    Map<Integer, Integer> map;
    
    public StockSpanner() {
        stack = new Stack<>();
        map = new HashMap<>();
    }
    
    public int next(int price) {
        int remove = 0;
        while (!stack.isEmpty() && stack.peek() <= price) {
            int top = stack.pop();
            remove++;
            if (map.containsKey(top)) {
                remove += map.get(top);
            }
        }
        stack.push(price);
        map.put(price, remove);
        if (remove == 0) {
            return 1;
        } 
        //map.put(price, remove);
        return remove+1;
    }
}

// Two stack, one stack is used to store the price, other one used to store the corresponding weights
class StockSpanner {
    
    Stack<Integer> prices;
    Stack<Integer> weights;
    
    public StockSpanner() {
        prices = new Stack<>();
        weights = new Stack<>();
    }
    
    public int next(int price) {
        int w = 1;
        while (!prices.isEmpty() && prices.peek() <= price) {
            prices.pop();
            w += weights.pop();
        }
        prices.push(price);
        weights.push(w);
        return w;
    }
}