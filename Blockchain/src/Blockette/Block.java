package Blockette;
import java.util.Date;     //deals w date and time(Date is a class)

//Block that makes up the blockchain.
public class Block
{
	public String hash;           //digital signature
	public String previousHash;   //prev blocks hash
	public String data;           //block data
	public long timeStamp;       //no of milliseconds since 1/1/1970.
	public int nonce;
	
	//block constructor
	public Block(String previousHash,String data)
	 {
	   this.previousHash=previousHash;
	   this.data= data;
	   this.timeStamp= new Date().getTime(); //get.time() method of javas date class returns no of msecs since
	   this.hash= calculateHash();                                      //jan 1 1970
	 }
	public String calculateHash()
	{
		String calculatedhash = StringUtil.applysha256(previousHash+ Long.toString(timeStamp) +
				Integer.toString(nonce)+ data);
		return calculatedhash;
	}
	//proof of work
	public void mineBlock(int difficulty) {
		String target = new String(new char[difficulty]).replace('\0','0');
		while(!hash.substring(0, difficulty).equals(target)) {
			nonce ++;
			hash = calculateHash();
		}
		System.out.println("Block Mined!! : "+ hash);
	}
}



