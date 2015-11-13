/**
 * 
 */
package database;

import models.Location;
import models.User;

/**
 * @author bolat
 *
 */
public interface GetClient {
	
	public User getUser(String username);
	
	public Location getLocation(int id);

}
