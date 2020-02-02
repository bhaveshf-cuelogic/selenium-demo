package demo.client;

import demo.core.datasources.OpenEdgePecos;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {   
    	OpenEdgePecos p = new OpenEdgePecos();
    	p.search_by_npi("1801965413");
    }
}
