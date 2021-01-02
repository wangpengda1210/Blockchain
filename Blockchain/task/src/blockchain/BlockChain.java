package blockchain;

import java.io.Serializable;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class BlockChain implements Serializable {

    private static final long serialVersionUID = 1L;

    private Block firstBlock;
    private Block lastBlock;
    private static int startZeros = 0;
    private static int messageId = 0;

    private static final ArrayList<Message> messages = new ArrayList<>();

    public BlockChain() {
    }

    static class Block implements Serializable {

        static int lastIndex = 1;

        private final int id;
        private final long timeStamp;
        private final String previousHash;
        private Block nextBlock;
        private int magicNumber;
        private final long generationTime;
        private final int creator;
        private final ArrayList<Message> blockData;
        private String increaseMessage;
        private boolean increase = false;
        private boolean decrease = false;

        public Block(String previousHash, int creator) {
            LocalDateTime startTime = LocalDateTime.now();

            id = lastIndex;
            timeStamp = new Date().getTime();
            this.previousHash = previousHash;
            nextBlock = null;

            Random random = new Random();
            magicNumber = 0;
            while (!getHash().startsWith("0".repeat(startZeros))) {
                if (Thread.currentThread().isInterrupted()) {
                    break;
                }
                magicNumber = Math.abs(random.nextInt());
            }

            this.creator = creator;

            blockData = new ArrayList<>();

            generationTime = Duration.between(startTime, LocalDateTime.now())
                    .toSeconds();

            if (generationTime < 5) {
                increase = true;
            } else if (generationTime > 20) {
                decrease = true;
            }

        }

        public String getHash() {
            return StringUtil.applySha256(id + String.valueOf(timeStamp)
                    + previousHash + magicNumber);
        }

        public Block getNextBlock() {
            return nextBlock;
        }

        public void setNextBlock(Block nextBlock) {
            this.nextBlock = nextBlock;
        }

        @Override
        public String toString() {

            StringBuilder sb = new StringBuilder("Block:\n")
                    .append("Created by: miner").append(creator).append("\n")
                    .append("miner").append(creator).append(" gets 100 VC\n")
                    .append("Id: ").append(id).append("\n")
                    .append("Timestamp: ").append(timeStamp).append("\n")
                    .append("Magic number: ").append(magicNumber).append("\n")
                    .append("Hash of the previous block: \n").append(previousHash)
                    .append("\n" + "Hash of the block: \n").append(getHash())
                    .append("\n").append("Block data: ");

            if (blockData.isEmpty()) {
                sb.append("no messages\n");
            } else {
                sb.append("\n");
                for (Message data : blockData) {
                    sb.append(data.getText()).append("\n");
                }
            }

            sb.append("Block was generating for ").append(generationTime)
                    .append(" seconds\n").append(increaseMessage);

            return sb.toString();

        }

    }

    public void addBlock(Block block) {
        if (firstBlock == null) {
            firstBlock = block;
            lastBlock = firstBlock;
        } else {
            lastBlock.setNextBlock(block);
            lastBlock = lastBlock.getNextBlock();
        }

        if (block.decrease) {
            startZeros--;
            block.increaseMessage = "N was decrease by 1";
        } else if (block.increase) {
            startZeros++;
            block.increaseMessage = "N was increased to " + startZeros;
        } else {
            block.increaseMessage = "N stays the same";
        }

        block.blockData.addAll(messages);
        messages.clear();

        Block.lastIndex++;
    }

    public boolean validate() throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        Block currentBlock = firstBlock;

        while (currentBlock.nextBlock != null) {
            if (currentBlock.getHash().equals(currentBlock.nextBlock.previousHash)) {
                for (Message message : currentBlock.blockData) {
                    if (!SignatureUtil.verify(message.getText() + message.getId(),
                            message.getSignature(), message.getPublicKey()) ||
                            message.getId() > messageId) {
                        return false;
                    }
                }
                currentBlock = currentBlock.nextBlock;
            } else {
                return false;
            }
        }

        return true;
    }

    public Block getLastBlock() {
        return lastBlock;
    }

    public void addMessage(Message message) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        if (SignatureUtil.verify(message.getText() + message.getId(),
                message.getSignature(), message.getPublicKey()) &&
                message.getId() <= messageId) {
            messages.add(message);
        }
    }

    public synchronized static int getMessageId() {
        messageId++;
        return messageId;
    }

}
