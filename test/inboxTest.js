const inbox = artifacts.require("./inbox.sol");

contract("inbox", accounts => {
  it("...should emit an event when you send an IPFS address.", async () => {
    const inbox = await inbox.deployed();

    eventEmitted = false
    var event = inbox.ipfsSent()
        await event.watch((err, res) => {
            eventEmitted = true
        })

    await inbox.sendIPFS(accounts[1], "SampleAddress", { from: accounts[0] });

    assert.equal(eventEmitted, true, "Sending an IPFS request does not emit an event.");
  });
});
