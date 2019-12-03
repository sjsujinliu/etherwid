pragma solidity ^0.5.12;

contract transfer {
  address public owner;
  uint public last_completed_transfer;

  modifier restricted() {
    if (msg.sender == owner) _;
  }

  constructor() public {
    owner = msg.sender;
  }

  function setCompleted(uint completed) public restricted {
    last_completed_transfer = completed;
  }

  function upgrade(address new_address) public restricted {
    transfer upgraded = transfer(new_address);
    upgraded.setCompleted(last_completed_transfer);
  }
}
