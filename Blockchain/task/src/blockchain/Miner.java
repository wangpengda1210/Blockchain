package blockchain;

import java.util.concurrent.Callable;

public class Miner implements Callable<BlockChain.Block> {

    int id;
    BlockChain blockChain;

    public Miner(int id, BlockChain blockChain) {
        this.id = id;
        this.blockChain = blockChain;
    }

    @Override
    public BlockChain.Block call() {
        BlockChain.Block block;
        if (blockChain.getLastBlock() == null) {
            block = new BlockChain.Block("0", id);
        } else {
            block = new BlockChain.Block(blockChain.getLastBlock().getHash(), id);
        }
        return block;
    }

}
