package Blockette;
import java.util.ArrayList;
import com.google.gson.GsonBuilder; //JSON representation of java object

public class chain {
	public static ArrayList<Block> blockchain = new ArrayList<Block>(); //dynamic array of type block
	public static int difficulty = 2;
	public static void main(String[] args)
	{
		//add our blocks to the blockchain arraylist
		blockchain.add(new Block("0","Hi Im the genesis block "));
		System.out.println("Trying to mine block 1...");
		blockchain.get(0).mineBlock(difficulty);
		
		blockchain.add(new Block(blockchain.get(blockchain.size()-1).hash, "Im the second block"));
		System.out.println("Trying to mine block 2...");
		blockchain.get(1).mineBlock(difficulty);
		
		blockchain.add(new Block(blockchain.get(blockchain.size()-1).hash, "And Im the third block"));
		System.out.println("Trying to mine block 3...");
		blockchain.get(2).mineBlock(difficulty);
		
		System.out.println("\nBlockchain is Vaild:" + isChainValid());
		
		String blockchainJson= new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
		System.out.println("\n The block chain: ");
		System.out.println(blockchainJson);
	}
	
	//Boolean method that loops through all the blocks and compares the hashes
	public static Boolean isChainValid() {
		Block currentBlock;
		Block prevBlock;
		String hashTarget = new String(new char[difficulty]).replace('\0','0');
		
		//loops through chain
		for(int i=1;i< blockchain.size(); i++) {
			currentBlock = blockchain.get(i);
			prevBlock = blockchain.get(i-1);
			//compare calculated hash and registered hash
			if(!currentBlock.hash.equals(currentBlock.calculateHash())) {
				System.out.println("Current hashes are not equal");
				return false;
			   }
			if(!prevBlock.hash.equals(currentBlock.previousHash)) {
				System.out.println("Previous hashes are not equal");
				return false;
				}
			if(!currentBlock.hash.substring(0, difficulty).equals(hashTarget)) {
				System.out.println("This block hasnt been mined");
				return false;
			    }
			}
		return true;
	}
  }
