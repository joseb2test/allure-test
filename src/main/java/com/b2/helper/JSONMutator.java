package com.b2.helper;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream.PutField;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.BooleanNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.LongNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.fasterxml.jackson.databind.node.ValueNode;
import com.google.common.collect.Lists;

public class JSONMutator
{
	private int level;
	private String[] paths;
	public static final String ADD_NODE = "AddNode";
	public static final String UPDATE_NODE = "UpdateNode"; 
	public static final String DUPLICATE_NODE = "DuplicateNode";
	public static final String REMOVE_NODE = "RemoveNode"; 
	
	private Object val;
	
	
	public static void main(String[] args) throws Exception
	{
		
		/* code for set
		String filePath = System.getProperty("user.dir")+"/src/test/resources/com/b2/api/test.json";
		ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(new FileInputStream(filePath));
        
        String path = "/batters/batter/0";
    	JSONMutator.modifyJSON(JSONMutator.DUPLICATE_NODE, path, "class", "divine", node.toString());
		System.out.println(node.toPrettyString());
		*/
		
		/* code for get*/
		String filePath = System.getProperty("user.dir")+"/src/test/resources/com/b2/api/test.json";
		ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(new FileInputStream(filePath));
        
        String path = "/batters/batter/0/type";
    	Object object = JSONMutator.getValue(node.toString(), path);
		System.out.println(object);

		
	}
	
	public JSONMutator(String path) 
	{
		level = 1;
		paths = path.split("/");
	}
    
    public static String modifyJSON(String operation, String path, String key, Object value,String json) throws Exception
    {
        
    	ObjectMapper mapper = new ObjectMapper();
    	JsonNode root = mapper.readTree(json);
    	JSONMutator mutator = new JSONMutator(path);
    	mutator.mutate(root, operation, key, value);
    	return root.toString();
    }

    
    private void mutate(JsonNode node, String operation, String key, Object value)
    {
    	
        if (node.isObject())
        {
        	Iterator<Map.Entry<String, JsonNode>> iterator = node.fields();

            ArrayList<Map.Entry<String, JsonNode>> nodesList = Lists.newArrayList(iterator);
            
            
            for (Map.Entry<String, JsonNode> nodeEntry : nodesList)
            {
                String name = nodeEntry.getKey();
                JsonNode newNode = nodeEntry.getValue();
                
                if(!name.equals(paths[level].trim()))
                {
                	continue;
                }
                
                if(level == paths.length -1)
                {
                	if(operation.equals(JSONMutator.UPDATE_NODE))
                    {
                    	if(name.equals(paths[level]))
                    	{
                    		((ObjectNode)node).put(name, getValueNode(value));
                    		
                    	}
                    	return;
                    }
                	else if(operation.equals(JSONMutator.ADD_NODE))
                    {
                    	if(name.equals(paths[level]))
                    	{
                    		((ObjectNode)node).put(key, getValueNode(value));
                    		
                    	}
                    	return;
                    }
                    else if(operation.equals(JSONMutator.REMOVE_NODE))
                    {
                    	if(name.equals(paths[level]))
                    	{
                    		((ObjectNode)node).remove(name);
                    	}
                    	return;
                    }
                }
                level++;
                this.mutate(newNode, operation, key, value);
            	
            }
        }
        else if (node.isArray())
        {
        	int i = 0;
        	int j = new Integer(paths[level].trim()).intValue();
        			
            Iterator<JsonNode> arrayItemsIterator = node.elements();
            ArrayList<JsonNode> arrayItemsList = Lists.newArrayList(arrayItemsIterator);
            for (JsonNode arrayNode : arrayItemsList)
            {
            	if( i == j)
                {
            		
            		if(level == paths.length -1)
                    {
            			if(operation.equals(JSONMutator.DUPLICATE_NODE))
            			{
            				((ArrayNode)node).add(arrayNode);
            				return;
            			}
            			else if(operation.equals(JSONMutator.ADD_NODE))
            			{
            				ObjectMapper objectMapper = new ObjectMapper();
            				ObjectNode obj = objectMapper.createObjectNode();
            				obj.put(key,getValueNode(value));
            				((ArrayNode)node).add(obj);
            				return;
            			}
            			
                    }
            		
            		level++;
            		this.mutate(arrayNode, operation, key, value);
                }
            	i++;
                
            }
        }
        
    }
    
    private ValueNode getValueNode(Object obj)
    {
    	if(obj instanceof Integer)
    	{
    		return new LongNode(new Long( String.valueOf(obj)));
    	}
    	else if(obj instanceof Integer || obj instanceof Long)
    	{
    		return new LongNode((Long)obj);
    	}
    	else if(obj instanceof String)
    	{
    		return new TextNode((String)obj);
    	}
    	else if(obj instanceof Boolean)
    	{
    		if(((Boolean)obj).booleanValue())
    			return BooleanNode.TRUE;
    		else
    			return BooleanNode.FALSE;
    	}
    	return null;
    }
    
    public static String getValue(String jsonString, String path) throws Exception
    {
        
    	ObjectMapper mapper = new ObjectMapper();
    	JsonNode root = mapper.readTree(jsonString);
    	JSONMutator mutator = new JSONMutator(path);
    	mutator.get(root);
    	return mutator.val.toString();
    	
    }

    
    private void get(JsonNode node)
    {
    	
        if (node.isObject())
        {
        	Iterator<Map.Entry<String, JsonNode>> iterator = node.fields();

            ArrayList<Map.Entry<String, JsonNode>> nodesList = Lists.newArrayList(iterator);
            
            
            for (Map.Entry<String, JsonNode> nodeEntry : nodesList)
            {
                String name = nodeEntry.getKey();
                JsonNode newNode = nodeEntry.getValue();
                
                if(!name.equals(paths[level].trim()))
                {
                	continue;
                }
                
                if(level == paths.length -1)
                {
            		if(name.equals(paths[level]))
                	{
                		val = ((ObjectNode)node).get(name);                		
                	}
                	return;
                }
                level++;
                this.get(newNode);
            	
            }
        }
        else if (node.isArray())
        {
        	int i = 0;
        	int j = new Integer(paths[level].trim()).intValue();
        			
            Iterator<JsonNode> arrayItemsIterator = node.elements();
            ArrayList<JsonNode> arrayItemsList = Lists.newArrayList(arrayItemsIterator);
            for (JsonNode arrayNode : arrayItemsList)
            {
            	if( i == j)
                {
            		
            		if(level == paths.length -1)
                    {
            			
            			val = ((ArrayNode)node).get(i);
            			return;
                    }
            		
            		level++;
            		this.get(arrayNode);
                }
            	i++;
            }
        }
        
    }

}