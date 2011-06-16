package dao;

import javax.annotation.PostConstruct;
import javax.ejb.Lock;
import javax.ejb.LockType;

import com.google.inject.Singleton;

@Singleton
public class MySingleton {
	  static private int tmpImgId;

	  @Lock(LockType.WRITE)
	  static public int tmpImgId() {
		  return tmpImgId++;
	  }
	  
	  @PostConstruct
	  public void init(){
		  tmpImgId = 0;
	  }
}
