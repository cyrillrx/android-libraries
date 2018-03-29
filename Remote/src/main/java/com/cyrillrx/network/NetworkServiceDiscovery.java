package com.cyrillrx.network;

import android.content.Context;
import android.net.nsd.NsdManager;
import android.net.nsd.NsdServiceInfo;
import android.util.Log;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Cyril Leroux
 *         Created on 08/04/2015.
 */
public class NetworkServiceDiscovery {

    private static final String TAG = NetworkServiceDiscovery.class.getSimpleName();
    private static final String SERVICE_TYPE = "_http._tcp.";

    private Context context;

    private NsdManager.RegistrationListener registrationListener;
    private NsdManager.DiscoveryListener discoveryListener;
    private NsdManager.ResolveListener resolveListener;

    private ServerSocket serverSocket;
    private NsdManager nsdManager;
    private NsdServiceInfo nsdServiceInfo;
    private List<NsdServiceInfo> discoveredServices;

    private String serviceName;
    private int localPort;

    public void initRegistrationListener() {

        registrationListener = new NsdManager.RegistrationListener() {
            @Override
            public void onRegistrationFailed(NsdServiceInfo serviceInfo, int errorCode) {
                // Registration failed!
                // Put debugging code here to determine why.
            }

            @Override
            public void onUnregistrationFailed(NsdServiceInfo serviceInfo, int errorCode) {
                // Unregistration failed.
                // Put debugging code here to determine why.
            }

            @Override
            public void onServiceRegistered(NsdServiceInfo serviceInfo) {
                // Save the service name.  Android may have changed it in order to
                // resolve a conflict, so update the name you initially requested
                // with the name Android actually used.
                serviceName = nsdServiceInfo.getServiceName();
            }

            @Override
            public void onServiceUnregistered(NsdServiceInfo serviceInfo) {
                // Service has been unregistered.
                // This only happens when you call NsdManager.unregisterService() and pass in this listener.
            }
        };
    }

    public void initDiscoveryListener() {

        discoveredServices = new ArrayList<>();

        discoveryListener = new NsdManager.DiscoveryListener() {
            @Override
            public void onStartDiscoveryFailed(String serviceType, int errorCode) {
                Log.e(TAG, "Discovery failed: Error code:" + errorCode);
                nsdManager.stopServiceDiscovery(this);
            }

            @Override
            public void onStopDiscoveryFailed(String serviceType, int errorCode) {
                Log.e(TAG, "Discovery failed: Error code:" + errorCode);
                nsdManager.stopServiceDiscovery(this);
            }

            @Override
            public void onDiscoveryStarted(String serviceType) {
                Log.d(TAG, "Service discovery started");
            }

            @Override
            public void onDiscoveryStopped(String serviceType) {
                Log.i(TAG, "Discovery stopped: " + serviceType);
            }

            @Override
            public void onServiceFound(NsdServiceInfo serviceInfo) {
                // A service was found!  Do something with it.
                Log.d(TAG, "Service discovery success" + serviceInfo);
                if (!serviceInfo.getServiceType().equals(SERVICE_TYPE)) {
                    // Service type is the string containing the protocol and
                    // transport layer for this service.
                    Log.d(TAG, "Unknown Service Type: " + serviceInfo.getServiceType());
                } else if (serviceInfo.getServiceName().equals(serviceName)) {
                    // The name of the service tells the user what they'd be
                    // connecting to. It could be "Bob's Chat App".
                    Log.d(TAG, "Same machine: " + serviceName);
                } else if (serviceInfo.getServiceName().contains("NsdChat")) {
                    nsdManager.resolveService(serviceInfo, resolveListener);
                }
            }

            @Override
            public void onServiceLost(NsdServiceInfo serviceInfo) {
                // When the network service is no longer available.
                // Internal bookkeeping code goes here.
                Log.e(TAG, "service lost" + serviceInfo);
            }
        };
    }

    public void initResolveListener() {
        resolveListener = new NsdManager.ResolveListener() {

            @Override
            public void onResolveFailed(NsdServiceInfo serviceInfo, int errorCode) {
                // Called when the resolve fails.  Use the error code to debug.
                Log.e(TAG, "Resolve failed" + errorCode);
            }

            @Override
            public void onServiceResolved(NsdServiceInfo serviceInfo) {
                Log.e(TAG, "Resolve Succeeded. " + serviceInfo);

                if (serviceInfo.getServiceName().equals(serviceName)) {
                    Log.d(TAG, "Same IP.");
                    return;
                }
                nsdServiceInfo = serviceInfo;
                int port = nsdServiceInfo.getPort();
                InetAddress host = nsdServiceInfo.getHost();
            }
        };
    }

    public void initServerSocket() {

        // Initialize a server socket on the next available port.
        try {
            serverSocket = new ServerSocket();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Store the chosen port.
        localPort = serverSocket.getLocalPort();
    }

    public void registerService(String serviceName, int port) {

        nsdServiceInfo = new NsdServiceInfo();
        nsdServiceInfo.setServiceName(serviceName);
        nsdServiceInfo.setServiceType(SERVICE_TYPE);
        nsdServiceInfo.setPort(port);

        nsdManager = (NsdManager) context.getSystemService(Context.NSD_SERVICE);

        // Register service on the network
        nsdManager.registerService(
                nsdServiceInfo,
                NsdManager.PROTOCOL_DNS_SD,
                registrationListener);

    }

    /** Discover devices */
    public void discoverServices() {
        nsdManager.discoverServices(
                SERVICE_TYPE,
                NsdManager.PROTOCOL_DNS_SD,
                discoveryListener);
    }

    public void tearDown() {
        nsdManager.unregisterService(registrationListener);
        nsdManager.stopServiceDiscovery(discoveryListener);
    }

    // Activity Lifecycle

    protected void onPause(Connection connection) {
        tearDown();
    }

    protected void onResume() {
        registerService("Cyril's Nexus", serverSocket.getLocalPort());
        discoverServices();
    }

    protected void onDestroy() {
        tearDown();
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
