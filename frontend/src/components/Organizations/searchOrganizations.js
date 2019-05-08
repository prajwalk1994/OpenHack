import React, { Component } from 'react';
import url from '../../config/config'
import axios from 'axios';

class searchOrganizations extends Component {
    constructor(props) {
        super(props);
        this.state = {
            organizationSearched: "EMPTY NAME",
            organizationsList:[],
            AllOrgsData:"",
            visibility: false
        }
    }

    componentDidMount = () => {
        //get all organizations list
        axios.get(url + "/organizations")
            .then((response) => {
                console.log(response.data)
                
                this.setState({
                    AllOrgsData:response.data
                })

                var tempOrg=[]
                for (let i of response.data){
                    tempOrg.push(i.name)
                }
                this.setState({
                    organizationsList:tempOrg
                })
                console.log("state after setting orgs",this.state)
            })
            .catch((err) => {
                if(err.response){
                console.log("err", err.response)
                }else{
                    alert("something went wrong")
                }
            })
    }

    searchOrgnizations = () => {
        
    }

    sendJoinRequest = () => {
        //send id of organization selected to backend
    }

    render() {
        var organizationsListDiv = "";
        organizationsListDiv=this.state.organizationsList.map((item)=>{
            return(
                <div className="row">
                    <h4 className="">{item}</h4>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <button className="btn btn-primary" onClick={this.sendJoinRequest}>join</button>
                </div>
            )
        })

        var searchedOrgDiv="";
        searchedOrgDiv=(
            <div className="row justify-content-center">
                <label className="mb-4 mr-3" name="searchedName">{this.state.organizationSearched}</label>
                <button className="btn btn-primary" onClick={this.sendJoinRequest}>join</button>
            </div>
            )
        return (
            <div>
                <h3>Search Organizations</h3>
                <div>
                    <div className="row justify-content-center">
                        <div className="col-sm-4 ">
                            <input type="text" className="form-control mb-4" name="searchOrg" placeholder="search organizations"></input>
                            <button className="btn btn-primary" onClick={this.searchOrgnizations}>Search</button>                            </div>
                    </div>
                    <div>
                        <label>
                        </label>
                    </div>
                    {!this.state.visibility ? <h3>List of all Organizations</h3> : <div></div>} 
                    <div className="row justify-content-center">
                        { !this.state.visibility ? organizationsListDiv : searchedOrgDiv}
                    </div>
                    
                </div>
            </div>
        );
    }
}

export default searchOrganizations;