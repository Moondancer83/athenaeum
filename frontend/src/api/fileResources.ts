import BaseResources, { isJSON, ResourceEventTarget } from "./BaseResources";
import FileData from "./FileData";

interface DocumentData {
  id: number;
  data: any;
  modifiedAt: string;
  name: string;
  owner: string;
  size: number;
}

interface DocumentListResponse {
  page: {
    size: number;
    totalElements: number;
    totalPages: number;
    number: number;
  };
  _embedded: {
    documents: DocumentData[];
  };
  _links: any;
}

interface DocumentUploadRequest {
  name: string;
  data: Blob;
  owner: string;
}

class FileResources extends BaseResources {
  public list(callback?: (value: FileData[]) => void): void {
    const onLoadEnd = (e: ProgressEvent<EventTarget>) => {
      if (callback) {
        const target = e.target as ResourceEventTarget;
        callback(JSON.parse(target.response));
      }
    };

    FileResources.call("GET", "/api/documents", onLoadEnd);
  }

  upload(file: FileData, callback?: () => void) {
    console.info("uploading", file);
    const data = new FormData();
    data.append("file", file.data);

    const onLoadEnd = (e: ProgressEvent<XMLHttpRequest>) => {
      console.log("response", e);
      console.log("target", e.target);
      if (e.target) {
        console.log("status", e.target.status);
        console.log("statusText", e.target.statusText);
        if (isJSON(e.target.response)) {
          console.log("response", JSON.parse(e.target.response));
          console.log("responseMessage", JSON.parse(e.target.response.message));
        }
      }

      if (callback) {
        callback();
      }
    };
    FileResources.call("POST", "/api/documents", onLoadEnd, data);
  }
}

export default new FileResources();
