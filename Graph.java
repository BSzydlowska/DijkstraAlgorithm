package algorytmDijkstra;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

public class Graph {

	private Map<String, LinkedList<Node>> graf = new LinkedHashMap<String, LinkedList<Node>>();
	Map<Node,String> parents = new HashMap<>();
	
	public static void main(String[] args) {

		Graph graf = new Graph();
		graf.init2();
		graf.print();
		graf.count();
		
	}

	private void print() {
		System.out.println("Print()");
		for(Map.Entry<String, LinkedList<Node>> entry : graf.entrySet()){
			System.out.println(entry.getKey().toString() + " " + entry.getValue().toString());
		}		
	}

	private String count() {
		
		LinkedList<Node> costs = new LinkedList<>();
		Map<Node,String> parents = new HashMap<>();
		LinkedList<String> toDo = new LinkedList<>();
		//StringBuilder sb;
		
		//fill nodes
		for(Map.Entry<String, LinkedList<Node>> entry : graf.entrySet()){ 
			toDo.add(entry.getKey());
		}
		
		//fill costs
		for(Map.Entry<String, LinkedList<Node>> entry : graf.entrySet()){
			LinkedList<Node> tmp = entry.getValue();
			for (Node node : tmp){
				Node n1 = new Node(node.getNodeName(),1000);
				if (costs.contains(n1)){
				} else {
					costs.add(n1);
				}
			}
		}
		
		String firstNode = toDo.getFirst();
		LinkedList<Node> tmp = graf.get(firstNode);
		for (int i=0; i<tmp.size(); i++){
			if (tmp.get(i).getCost() < costs.get(i).getCost()){
				costs.get(i).setCost(tmp.get(i).getCost());
				parents.put(tmp.get(i), firstNode);
			}
		}
		toDo.removeFirst();
		System.out.println("\nToDo after remove " + toDo.toString());
		
		while(!toDo.isEmpty()){
			Node cheapestNode = getLowestCostNode(costs, toDo);
			System.out.println("\nnajtansze: " + cheapestNode);

			tmp = graf.get(cheapestNode.getNodeName());
			System.out.println(tmp);
			System.out.println(costs);
			for (int i=0; i<tmp.size(); i++){
				for(int j=0; j<costs.size(); j++){
					if(costs.get(j).getNodeName()==tmp.get(i).getNodeName()){
						if(costs.get(j).getCost()>tmp.get(i).getCost() + cheapestNode.getCost()){
							parents.remove(costs.get(j));
							costs.get(j).setCost(tmp.get(i).getCost() + cheapestNode.getCost());
							parents.put(costs.get(j), cheapestNode.getNodeName());
						}
					}
				}
			}
			for (int i=0; i<toDo.size(); i++){
				if(toDo.get(i).equals(cheapestNode.getNodeName())){
					toDo.remove(i);
				}
			}
						
		}
				
		//check
		System.out.println("\nCosts: " + costs.toString());
		System.out.println("Parents: " + parents.toString());
		//System.out.println("\nToDo " + toDo.toString());
		
		return null;
	}

	private Node getLowestCostNode(LinkedList<Node> costs, LinkedList<String> toDo) {
		
		int lowestCost = 1000;
		int lowestIndex = 0;
		for (int i=0; i<costs.size(); i++){
			if(costs.get(i).getCost() < lowestCost){
				for (int j=0; j<toDo.size(); j++){
					if(toDo.get(j).equals(costs.get(i).getNodeName())){
						lowestIndex = i;
						lowestCost = costs.get(i).cost;			
					}
				}
			}
		}
		return costs.get(lowestIndex);
	}

	private void init() {

		Node n1 = new Node("A", 6);
		Node n2 = new Node("B", 2);
		Node n3 = new Node("A", 3);
		Node n4 = new Node("meta", 5);
		Node n5 = new Node("meta", 1);

		LinkedList<Node> listStart = new LinkedList<>();
		listStart.add(n1);
		listStart.add(n2);

		LinkedList<Node> listA = new LinkedList<>();
		listA.add(n5);

		LinkedList<Node> listB = new LinkedList<>();
		listB.add(n3);
		listB.add(n4);

		graf.put("Start", listStart);
		graf.put("A", listA);
		graf.put("B", listB);

	}
	
	private void init2() {

		Node n1 = new Node("A", 5);
		Node n2 = new Node("C", 2);
		Node n3 = new Node("B", 4);
		Node n4 = new Node("meta", 3);
		Node n5 = new Node("A", 8);
		Node n6 = new Node("D", 7);
		Node n7 = new Node("D", 2);
		Node n8 = new Node("D", 6);
		Node n9 = new Node("meta", 1);
		
		LinkedList<Node> listStart = new LinkedList<>();
		listStart.add(n1);
		listStart.add(n2);

		LinkedList<Node> listA = new LinkedList<>();
		listA.add(n3);
		listA.add(n7);
		
		LinkedList<Node> listB = new LinkedList<>();
		listB.add(n8);
		listB.add(n4);
		
		LinkedList<Node> listC = new LinkedList<>();
		listC.add(n5);
		listC.add(n6);
		
		LinkedList<Node> listD = new LinkedList<>();
		listD.add(n9);

		graf.put("Start", listStart);
		graf.put("A", listA);
		graf.put("B", listB);
		graf.put("C", listC);
		graf.put("D", listD);

	}

}
