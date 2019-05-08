import React, { Component } from 'react';
import Axios from 'axios';
import url from '../../config/config';

class MembershipApprovals extends Component {
    constructor(props){
        super(props);
        this.state={
            userid:localStorage.getItem("userid"),
            username:["ranjith","prajwal","vinay"],
            orgName:["sjsu","isss","isis"],
            status:[]
        }
    }

    componentDidMount=()=>{
        Axios.get()
    }

    handleApprove = (e)=>{
        e.preventDefault();

    }

    handleApprove=(e)=>{
        e.preventDefault();
        Axios.get(url+"/organizationRequests/"+this.state.userid)
        .then((response)=>{
            var tempusername = []
            var temporgName = []
            var tempstatus =[]
            for(let i of response.data){
                tempusername.push(i.user.username);
                temporgName.push(i.organization.name);
                tempstatus.push(i.approval)
            }

            this.setState({
                username:tempusername,
                orgName:temporgName,
                status:tempstatus
            })
        })
        .catch((error)=>{
            if(error.response){
                console.log(error.response.status)
            }else{
                alert("something went wrong")
            }
        })
    }

    render() {
        var MembershipRequests = "";
        MembershipRequests = this.state.username.map((item,i)=>{
            return(
                <div className="row justify-content-center">
                    <label className="col-sm-3">{item}</label>
                    <label className="col-sm-3">{this.state.orgName[i]}</label>
                    <button className="btn btn-success mr-2" onClick={this.handleApprove}>{this.state.status[i]}</button>
                    {/* <button className="btn btn-danger" onClick={this.handleDeny}>Deny</button> */}
                </div>
            )
        })

        return (
            <div className="container">
            <h3>Membership Approvals</h3>
                <div className="row justify-content-center">
                    <h6 className="col-sm-3">Username</h6>
                    <h6 className="col-sm-3">Organization name</h6>
                </div>
                {MembershipRequests}
            </div>
        );
    }
}

export default MembershipApprovals;