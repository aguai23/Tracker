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
public interface UpdateClient {
	
	public void updateUser(String username, User user);
	
	public void updateLocation(int id, Location loc);

}
