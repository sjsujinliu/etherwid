pragma solidity ^0.5.12;

contract ipfs_inbox {

    // Grabs the name of the resources based on address
    mapping (address => string) ipfs_inbox;

    // Events
    event ipfsSent(string _ipfsHash, address _address);
    event inboxResponse(string response);

    // Modifiers
    modifier notFull (string memory _string) {bytes memory stringTest = bytes(_string); require (stringTest.length == 0); _;}

    constructor() public {

    }

    function sendIPFS(address _address, string memory _ipfsHash)
        notFull(ipfs_inbox[_address])
        public
    {   
       ipfs_inbox[_address] = _ipfsHash;
       emit ipfsSent(_ipfsHash, _address);
    }
    
    function checkInbox()
        public
    {
        string memory ipfs_hash = ipfs_inbox[msg.sender];
        if(bytes(ipfs_hash).length == 0) {
            emit inboxResponse("Empty Inbox");
        } else {
            ipfs_inbox[msg.sender] = "";
            emit inboxResponse(ipfs_hash);
        }
    }
}