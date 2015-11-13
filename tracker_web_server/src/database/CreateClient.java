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
public interface CreateClient {
	
	public void createUser(User user);
	
	public void createLocation(Location loc);

}
