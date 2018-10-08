package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import model.PrintDocument;

public class GeneralUtil {

	//merge and sort list
	public static List<PrintDocument> sortDocs(List<PrintDocument> list) {
		return customSortDocs(list);
	}
	
	public static List<PrintDocument> mergeDocs(List<PrintDocument> list1, List<PrintDocument> list2) {
		List<PrintDocument> list = new ArrayList<>();
		list.addAll(list1);
		list.addAll(list2);
		return customSortDocs(list);
	}
	
	
	
	private static List<PrintDocument> customSortDocs(List<PrintDocument> list) {
		//space complexity: 5n? not sure
		//time complexity : 3n
		
		//Group the items based on their document name
		HashMap<String, DocGroup> groups = new HashMap<>();
		for(PrintDocument doc : list) {
			if(!doc.getDocument().getFileType().isPrintable())
				continue; //this will remove it from the print job
			String key = doc.getDocument().getFileName();
			if(groups.containsKey(key))
				groups.get(key).addDoc(doc);
			else 
				groups.put(key, new DocGroup(doc));
		}
		
		//put into prio buckets
		TreeMap<Long, List<DocGroup>> prioGroups = new TreeMap<>();
		for(String s : groups.keySet()) {
			long prio = groups.get(s).getPrio();
			if(prioGroups.containsKey(prio)) 
				prioGroups.get(prio).add(groups.get(s));
			else {
				List<DocGroup> groupList = new ArrayList<>();
				groupList.add(groups.get(s));
				prioGroups.put(prio, groupList);
			}
		}
		
		//add in order of prio
		List<PrintDocument> docs = new ArrayList<>();
		for(long l : prioGroups.keySet()) 
			for(DocGroup g : prioGroups.get(l))
				docs.addAll(g.getDocs());
		
		return docs;
	}
	
	
	
}

class DocGroup {
	
	private List<PrintDocument> docs;
	private long lowestPrio;
	
	DocGroup(PrintDocument doc) {
		docs = new ArrayList<>();
		docs.add(doc);
		lowestPrio = doc.getPriority();
	}
	
	void addDoc(PrintDocument doc) {
		docs.add(doc);
		if(doc.getPriority() < lowestPrio)
			lowestPrio = doc.getPriority();
	}
	
	long getPrio() {return lowestPrio;}
	List<PrintDocument> getDocs() {return docs;}
}

