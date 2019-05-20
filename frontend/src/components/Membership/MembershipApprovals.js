import React, { Component } from 'react';
import Axios from 'axios';
import url from '../../config/config';

class MembershipApprovals extends Component {
    constructor(props) {
        super(props);
        this.state = {
            userid: localStorage.getItem("userid"),
            username: [],
            orgName: [],
            userids: [],
            status: [],
            orgids: [],
            disableButton: false
        }
    }

    componentDidMount = () => {
        // e.preventDefault();
        Axios.get(url + "/organizationRequests/" + this.state.userid)
            .then((response) => {
                var tempusername = []
                var temporgName = []
                var tempstatus = []
                var tempuserids = []
                var temporgids = []
                for (let i of response.data) {
                    tempuserids.push(i.user.id);
                    tempusername.push(i.user.username);
                    temporgName.push(i.organization.name);
                    temporgids.push(i.organization.id);
                    if (i.approval == "No") {
                        tempstatus.push("Approve")
                    } else {
                        tempstatus.push("Approved")
                        if (i.approval == "Yes") {
                            this.setState({
                                disableButton: true,
                            })
                        } else {
                            this.setState({
                                disableButton: false,
                            })
                        }

                    }
                }

                this.setState({
                    userids: tempuserids,
                    username: tempusername,
                    orgName: temporgName,
                    orgids: temporgids,
                    status: tempstatus
                })
            })
            .catch((error) => {
                if (error.response) {
                    console.log(error.response.status)
                } else {
                    alert("something went wrong")
                }
            })
    }



    handleApprove = (e, userId, orgId) => {
        e.preventDefault();
        Axios.post(url + "/activateMember/" + userId + "/" + this.state.userid + "/" + orgId)
            .then((response) => {
                console.log(response.data);
                alert("Membership confirmed");
                window.location.reload();
            })
            .catch((error) => {
                if (error.response) {
                    console.log(error.response.status)
                } else {
                    alert("something went wrong")
                }
            })
    }

    render() {
        var MembershipRequests = "";
        MembershipRequests = this.state.username.map((item, i) => {
            return (
                <div className="row justify-content-center">
                    <label className="col-sm-3">{item}</label>
                    <label className="col-sm-3">{this.state.orgName[i]}</label>
                    <button className="btn btn-success mr-2" disabled={(this.state.status[i] === "Approved") ? true : false} onClick={(e) => this.handleApprove(e, this.state.userids[i], this.state.orgids[i])} >{this.state.status[i]}</button>
                    {/* <button className="btn btn-danger" onClick={this.handleDeny}>Deny</button> */}
                </div>
            )
        })

        return (
            <div className="container mt-5">
                <div className="formContainer" style={{ marginLeft: "25%", marginRight: "25%" }}>
                    <h3>Membership Approvals</h3>
                    <div className="row justify-content-center">
                        <h6 className="col-sm-6">Username</h6>
                        <h6 className="col-sm-6">Organization name</h6>
                    </div>
                    {MembershipRequests}
                </div>
            </div>
        );
    }
}

export default MembershipApprovals;