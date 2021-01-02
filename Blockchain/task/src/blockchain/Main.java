package blockchain;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    private static GenerateKeys generateKeys;

    static {
        try {
            generateKeys = new GenerateKeys(1024);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException, IOException {
        BlockChain blockChain = new BlockChain();

        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < 5; i++) {
            int finalI = i;
            executorService.submit(() -> {
                try {
                    try {
                        Thread.sleep(finalI * 100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    blockChain.addMessage(new Message("message", generateKeys));
                } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
                    e.printStackTrace();
                }
            });
        }

        ArrayList<Miner> miners = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            miners.add(new Miner(i, blockChain));
        }

        for (int i = 0; i < 15; i++) {
            BlockChain.Block newBlock = executorService.invokeAny(miners);
            blockChain.addBlock(newBlock);
            System.out.println(newBlock);
            System.out.println();
            SerializationUtils.serialize(blockChain, SerializationUtils.FILE_DIR);
        }

        executorService.shutdown();
        executorService.shutdownNow();
    }
}
