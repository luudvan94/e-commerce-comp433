package dal.partner;

import dal.Repository;
import entity.partner.Partner;

public interface PartnerRepository extends Repository<Partner, String> {

	Partner partnerWithUsernamePassword(String username, String password);
	
	boolean isUserAlreadyExist(String username);
}
