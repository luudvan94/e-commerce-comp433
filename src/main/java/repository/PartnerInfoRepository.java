package repository;

import entity.PartnerInfo;

public interface PartnerInfoRepository extends Repository<PartnerInfo, String> {
	
	PartnerInfo partnerInfobyPartnerID(String id);

}
