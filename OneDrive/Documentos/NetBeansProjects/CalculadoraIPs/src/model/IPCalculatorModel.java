/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author 
 */
public class IPCalculatorModel {
    private String ipAddress;
    private int prefixLength;

    public IPCalculatorModel(String ipAddress, int prefixLength) {
        this.ipAddress = ipAddress;
        this.prefixLength = prefixLength;
    }

    private int[] parseIp(String ipAddress) {
        String[] parts = ipAddress.split("\\.");
        int[] result = new int[4];
        for (int i = 0; i < 4; i++) {
            result[i] = Integer.parseInt(parts[i]);
        }
        return result;
    }

    private int[] calculateMask(int prefixLength) {
        int[] mask = new int[4];
        int maskBits = 0xffffffff << (32 - prefixLength);
        mask[0] = (maskBits >>> 24) & 0xff;
        mask[1] = (maskBits >>> 16) & 0xff;
        mask[2] = (maskBits >>> 8) & 0xff;
        mask[3] = maskBits & 0xff;
        return mask;
    }
    
     private String toBinaryString(int[] address) {
        StringBuilder binaryString = new StringBuilder();
        for (int part : address) {
            binaryString.append(String.format("%8s", Integer.toBinaryString(part)).replace(' ', '0')).append(".");
        }
        return binaryString.substring(0, binaryString.length() - 1);
    }

    public String getNetworkAddress() {
        int[] ip = parseIp(ipAddress);
        int[] mask = calculateMask(prefixLength);
        int[] network = new int[4];
        for (int i = 0; i < 4; i++) {
            network[i] = ip[i] & mask[i];
        }
        return String.format("%d.%d.%d.%d", network[0], network[1], network[2], network[3]);
    }

    public String getBroadcastAddress() {
        int[] ip = parseIp(ipAddress);
        int[] mask = calculateMask(prefixLength);
        int[] broadcast = new int[4];
        for (int i = 0; i < 4; i++) {
            broadcast[i] = ip[i] | ~mask[i] & 0xff;
        }
        return String.format("%d.%d.%d.%d", broadcast[0], broadcast[1], broadcast[2], broadcast[3]);
    }

    public String getFirstHostAddress() {
        int[] network = parseIp(getNetworkAddress());
        network[3] += 1;
        return String.format("%d.%d.%d.%d", network[0], network[1], network[2], network[3]);
    }

    public String getLastHostAddress() {
        int[] broadcast = parseIp(getBroadcastAddress());
        broadcast[3] -= 1;
        return String.format("%d.%d.%d.%d", broadcast[0], broadcast[1], broadcast[2], broadcast[3]);
    }

    public String getNetmask() {
        int[] mask = calculateMask(prefixLength);
        return String.format("%d.%d.%d.%d", mask[0], mask[1], mask[2], mask[3]);
    }

    public String getWildcard() {
        int[] mask = calculateMask(prefixLength);
        int[] wildcard = new int[4];
        for (int i = 0; i < 4; i++) {
            wildcard[i] = ~mask[i] & 0xff;
        }
        return String.format("%d.%d.%d.%d", wildcard[0], wildcard[1], wildcard[2], wildcard[3]);
    }

    public int getNumberOfHosts() {
        return (int) Math.pow(2, (32 - prefixLength)) - 2;
    }
    
    public String getIpAddressBinary() {
        int[] ip = parseIp(ipAddress);
        return toBinaryString(ip);
    }

    public String getNetworkAddressBinary() {
        int[] network = parseIp(getNetworkAddress());
        return toBinaryString(network);
    }

    public String getBroadcastAddressBinary() {
        int[] broadcast = parseIp(getBroadcastAddress());
        return toBinaryString(broadcast);
    }

    public String getFirstHostAddressBinary() {
        int[] firstHost = parseIp(getFirstHostAddress());
        return toBinaryString(firstHost);
    }

    public String getLastHostAddressBinary() {
        int[] lastHost = parseIp(getLastHostAddress());
        return toBinaryString(lastHost);
    }

    public String getNetmaskBinary() {
        int[] netmask = parseIp(getNetmask());
        return toBinaryString(netmask);
    }

    public String getWildcardBinary() {
        int[] wildcard = parseIp(getWildcard());
        return toBinaryString(wildcard);
    }
}

